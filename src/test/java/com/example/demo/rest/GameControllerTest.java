package com.example.demo.rest;

import com.example.demo.ContainerDatabaseTest;
import com.example.demo.dto.BookDTO;
import com.example.demo.dto.GameDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest extends ContainerDatabaseTest {

    @Autowired
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void prepare() {
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Test
    void saveUpdateReadDelete() throws Exception {
        GameDTO newGame = new GameDTO(null,
                "Zelda",
                "The Legend of Zelda: Ocarina of Time",
                "Nintendo",
                "Nintendo",
                "Adventure",
                1998,
                5,
                "NOT_STARTED");

        String newGameAsJson = objectMapper.writeValueAsString(newGame);

        // create new book
        MvcResult resultActions = this.mockMvc.perform(post("/games/").content(newGameAsJson).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        GameDTO createdGame = objectMapper.readValue(resultActions.getResponse().getContentAsString(), GameDTO.class);
        int id = createdGame.id();

        // get all books and check if it is there
        this.mockMvc.perform(get("/games/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Matchers.containsString("The Legend of Zelda: Ocarina of Time")))
                .andExpect(content().string(Matchers.containsString("{\"id\":%s".formatted(id))));

        // update status twice and read after each update
        this.mockMvc.perform(put("/games/%s/IN_PROGRESS".formatted(id))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(get("/games/%s".formatted(id))).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Matchers.containsString("IN_PROGRESS")));
        this.mockMvc.perform(put("/games/%s/DONE".formatted(id))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(get("/games/%s".formatted(id))).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Matchers.containsString("DONE")));

        // delete book
        this.mockMvc.perform(delete("/games/%s".formatted(id))).andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // get 404 after looking for the deleted id
        this.mockMvc.perform(get("/games/%s".formatted(id))).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
