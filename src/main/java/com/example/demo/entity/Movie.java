package com.example.demo.entity;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Movie extends Item {
    
    @JoinColumn
    @ManyToOne
    private Person director;

    @JoinColumn
    @ManyToOne
    private Person producer;

    @ManyToMany
    private Collection<Person> actors;

    @Column
    private Integer duration;
    
}
