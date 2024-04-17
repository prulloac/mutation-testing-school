package com.example.demo.rest;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.FranchiseDTO;
import com.example.demo.service.FranchiseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RequestMapping("/franchises")
public class FranchiseController {
    
    private final FranchiseService service;

    public FranchiseController(FranchiseService service) {
        this.service = service;
    }

    public FranchiseService getService() {
        return service;
    }

    @Operation(summary = "Get a franchise by id", description = "Get a franchise by id")
    @GetMapping("/{id}")
    public ResponseEntity<FranchiseDTO> findById(@PathVariable @Parameter int id) {
        return ResponseEntity.ok(getService().findById(id));
    }

    @Operation(summary = "Get all franchises", description = "Get all franchises")
    @GetMapping("/")
    public ResponseEntity<Collection<FranchiseDTO>> findAll() {
        return ResponseEntity.ok(getService().findAll());
    }

}
