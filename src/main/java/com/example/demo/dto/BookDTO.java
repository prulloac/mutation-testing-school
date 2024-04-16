package com.example.demo.dto;

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
}
