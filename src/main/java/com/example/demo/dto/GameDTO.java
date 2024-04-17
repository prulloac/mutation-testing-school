package com.example.demo.dto;

import com.example.demo.entity.Game;

public record GameDTO(
    Integer id,
    String franchise,
    String title,
    String developer,
    String publisher,
    String genre,
    int year,
    int installment,
    String status) {
        public GameDTO(Game game) {
            this(game.getId(), 
            game.getFranchise().getName(), 
            game.getName(), 
            game.getDeveloper().getName(), 
            game.getPublisher().getName(), 
            game.getCategory(), 
            game.getReleaseDate().getYear(), 
            game.getInstallment(), 
            game.getStatus().name());
        }
}
