package com.example.demo.dto;

import java.util.Collection;

public record MovieDTO(
    int id,
    String franchise,
    String title,
    String director,
    String producer,
    String genre,
    Collection<String> cast,
    int year,
    int duration,
    String status
) {
}
