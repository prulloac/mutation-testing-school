package com.example.demo.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Franchise;

public interface FranchiseRepository extends JpaRepository<Franchise, Integer>{

    Optional<Franchise> findByName(String name);
    
}
