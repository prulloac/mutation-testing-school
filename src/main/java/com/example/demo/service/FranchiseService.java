package com.example.demo.service;

import java.util.Collection;

import com.example.demo.dto.FranchiseDTO;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.persistence.FranchiseRepository;

public class FranchiseService {

    private final FranchiseRepository repository;

    public FranchiseService(FranchiseRepository repository) {
        this.repository = repository;
    }

    public FranchiseDTO findById(int id) {
        return repository.findById(id).map(FranchiseDTO::new).orElseThrow(() -> new ItemNotFoundException("Franchise %s not found".formatted(id)) );
    }

    public Collection<FranchiseDTO> findAll() {
        return repository.findAll().stream().map(FranchiseDTO::new).toList();
    }

}
