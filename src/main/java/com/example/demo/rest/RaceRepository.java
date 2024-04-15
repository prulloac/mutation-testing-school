package com.example.demo.rest;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Race;

public interface RaceRepository extends JpaRepository<Race, Integer>{
    
}
