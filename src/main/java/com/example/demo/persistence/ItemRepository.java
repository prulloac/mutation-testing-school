package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.demo.entity.Item;

@NoRepositoryBean
public interface ItemRepository<T extends Item> extends JpaRepository<T, Integer>{

}
