package com.example.demo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import com.example.demo.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Collection;

@RequestMapping("/books")
@Controller
@Tag(name = "Book", description = "The book API")
public class BookController extends ItemController<Book, BookDTO> {
    
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get a book by id", description = "Get a book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/")
    public ResponseEntity<Collection<BookDTO>> findAll() {
        return super.findAll();
    }

    @Operation(summary = "Get a book by id", description = "Get a book by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the book", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))),
        @ApiResponse(responseCode = "404", description = "Book not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable @Parameter int id) {
        return super.findById(id);
    }

    @Operation(summary = "Update a book status by id", description = "Get a book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @PutMapping("/{id}/{status}")
    public ResponseEntity<BookDTO> updateStatusById(@PathVariable("id") @Parameter int id, @PathVariable("status") @Parameter String status) {
        return super.updateStatus(id, status);
    }

    @Operation(summary = "Delete a book by id", description = "Delete a book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Found the book", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") @Parameter int id) {
        return super.deleteById(id);
    }

    @Operation(summary = "Save a new book", description = "Save a new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/")
    public ResponseEntity<BookDTO> save(@RequestBody @Parameter BookDTO book) {
        return super.save(book);
    }

    @Override
    public ItemService<Book, BookDTO> getService() {
        return bookService;
    }
}
