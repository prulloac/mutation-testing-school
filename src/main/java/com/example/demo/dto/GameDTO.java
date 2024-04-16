package com.example.demo.dto;

public record GameDTO(
    Integer id,
    String franchise,
    String title,
    String developer,
    String publisher,
    String genre,
    int year,
    int installment,
    String status
) {
}
