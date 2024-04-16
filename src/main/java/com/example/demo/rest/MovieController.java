package com.example.demo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.MovieDTO;
import com.example.demo.entity.Movie;
import com.example.demo.service.ItemService;
import com.example.demo.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/movies")
@Controller
@Tag(name = "Movie", description = "The movie API")
public class MovieController extends ItemController<Movie, MovieDTO> {
    
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Get a movie by id", description = "Get a movie by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the movie", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))),
        @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable @Parameter int id) {
        return super.findById(id);
    }

    @Override
    public ItemService<Movie, MovieDTO> getService() {
        return movieService;
    }
}
