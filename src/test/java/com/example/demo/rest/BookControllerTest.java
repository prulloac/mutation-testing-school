package com.example.demo.rest;

import com.example.demo.ContainerDatabaseTest;
import com.example.demo.dto.BookDTO;
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
class BookControllerTest extends ContainerDatabaseTest {

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
        BookDTO newBook = new BookDTO(null,
                "Avatar",
                "The Legend of Aang: Water",
                "Aang",
                "True Story",
                "Nickelodeon",
                2005,
                "1234-3421",
                "NOT_STARTED");

        String newBookAsJson = objectMapper.writeValueAsString(newBook);

        // create new book
        MvcResult resultActions = this.mockMvc.perform(post("/books/").content(newBookAsJson).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        BookDTO createdBook = objectMapper.readValue(resultActions.getResponse().getContentAsString(), BookDTO.class);
        int id = createdBook.id();

        // get all books and check if it is there
        this.mockMvc.perform(get("/books/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Matchers.containsString("The Legend of Aang: Water")))
                .andExpect(content().string(Matchers.containsString("{\"id\":%s".formatted(id))));

        // update status twice and read after each update
        this.mockMvc.perform(put("/books/%s/IN_PROGRESS".formatted(id))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(get("/books/%s".formatted(id))).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Matchers.containsString("IN_PROGRESS")));
        this.mockMvc.perform(put("/books/%s/DONE".formatted(id))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(get("/books/%s".formatted(id))).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Matchers.containsString("DONE")));

        // delete book
        this.mockMvc.perform(delete("/books/%s".formatted(id))).andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // get 404 after looking for the deleted id
        this.mockMvc.perform(get("/books/%s".formatted(id))).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
