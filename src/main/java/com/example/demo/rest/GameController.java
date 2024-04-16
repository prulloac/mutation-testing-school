package com.example.demo.rest;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.GameDTO;
import com.example.demo.entity.Game;
import com.example.demo.service.GameService;
import com.example.demo.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping("/games")
@Controller
@Tag(name = "Game", description = "The game API")
public class GameController extends ItemController<Game, GameDTO> {

    private final GameService service;

    public GameController(GameService bookService) {
        this.service = bookService;
    }

    @Operation(summary = "Get a game by id", description = "Get a game by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the game", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "404", description = "Game not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/")
    public ResponseEntity<Collection<GameDTO>> findAll() {
        return super.findAll();
    }

    @Operation(summary = "Get a game by id", description = "Get a game by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "404", description = "Game not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> findById(@PathVariable @Parameter int id) {
        return super.findById(id);
    }

    @Operation(summary = "Update a game status by id", description = "Get a game by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the game", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "404", description = "Game not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @PutMapping("/{id}/{status}")
    public ResponseEntity<GameDTO> updateStatusById(@PathVariable("id") @Parameter int id, @PathVariable("status") @Parameter String status) {
        return super.updateStatus(id, status);
    }

    @Operation(summary = "Delete a game by id", description = "Delete a game by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Found the game", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "404", description = "Game not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") @Parameter int id) {
        return super.deleteById(id);
    }

    @Operation(summary = "Save a new game", description = "Save a new game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/")
    public ResponseEntity<GameDTO> save(@RequestBody @Parameter GameDTO game) {
        return super.save(game);
    }

    @Override
    public ItemService<Game, GameDTO> getService() {
        return service;
    }
}
