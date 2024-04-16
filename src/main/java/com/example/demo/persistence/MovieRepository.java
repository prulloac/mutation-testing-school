package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
    
}
