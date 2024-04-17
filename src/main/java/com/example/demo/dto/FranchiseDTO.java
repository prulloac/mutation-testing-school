package com.example.demo.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.demo.entity.Franchise;

public record FranchiseDTO(
    Integer id,
    String name,
    Collection<BookDTO> books,
    Collection<GameDTO> games) {
        public FranchiseDTO(Franchise franchise) {
            this(franchise.getId(), franchise.getName(), 
            franchise.getBooks().stream().map(BookDTO::new).collect(Collectors.toList()), 
            franchise.getGames().stream().map(GameDTO::new).collect(Collectors.toList()));
        }
}
