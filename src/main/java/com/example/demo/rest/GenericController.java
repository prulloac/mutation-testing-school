package com.example.demo.rest;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.example.demo.service.RepositoryService;

public interface GenericController<E, D, I> {
    RepositoryService<E, D, I> getService();

    default ResponseEntity<D> save(D object) {
        return ResponseEntity.status(201).body(getService().save(object));
    }

    default ResponseEntity<String> deleteById(I id) {
        getService().deleteById(id);
        return ResponseEntity.status(204).body("Deleted");
    }

    default ResponseEntity<D> findById(I id) {
        return ResponseEntity.ok(getService().findById(id));
    }

    default ResponseEntity<Collection<D>> findAll() {
        return ResponseEntity.ok(getService().findAll());
    }
}
