package com.example.demo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.GameDTO;
import com.example.demo.entity.Game;
import com.example.demo.service.ItemService;
import com.example.demo.service.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/games")
@Controller
@Tag(name = "Game", description = "The game API")
public class GameController extends ItemController<Game, GameDTO> {
    
    private final GameService service;

    public GameController(GameService gameService) {
        this.service = gameService;
    }

    @Operation(summary = "Get a game by id", description = "Get a game by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the game", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GameDTO.class))),
        @ApiResponse(responseCode = "404", description = "Game not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> findById(@PathVariable @Parameter int id) {
        return super.findById(id);
    }

    @Override
    public ItemService<Game, GameDTO> getService() {
        return service;
    }
}
