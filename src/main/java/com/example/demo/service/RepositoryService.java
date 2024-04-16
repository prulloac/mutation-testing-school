package com.example.demo.service;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryService<E, D, I> {
    public <R extends JpaRepository<E,I>> R getRepository();

    public D toDTO(E entity);

    public D save(D object);

    default D findById(I id) {
        return getRepository().findById(id).map(this::toDTO).orElseThrow(() -> new RuntimeException("Not found"));
    }

    default Collection<D> findAll() {
        return getRepository().findAll().stream().map(this::toDTO).toList();
    }

    default void deleteById(I id) {
        getRepository().deleteById(id);
    }
}
