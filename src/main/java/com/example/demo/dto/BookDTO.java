package com.example.demo.dto;

import com.example.demo.entity.Book;

public record BookDTO(
    Integer id, 
    String franchise, 
    String title, 
    String author, 
    String genre, 
    String publisher, 
    int year, 
    String isbn, 
    String status) {
        public BookDTO(Book book) {
            this(book.getId(), 
            book.getFranchise().getName(), 
            book.getName(), 
            book.getAuthor().getName(), 
            book.getCategory(), 
            book.getPublisher(), 
            book.getReleaseDate().getYear(), 
            book.getIsbn(), 
            book.getStatus().name());
        }
}
