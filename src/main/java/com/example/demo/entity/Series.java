package com.example.demo.entity;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Series extends Item {
    
    @ManyToMany
    private Collection<Person> actors;

    @ManyToMany
    private Collection<Person> producers;

    @ManyToOne
    private Person director;

    @OneToMany
    private Collection<Season> seasons;

    @Column
    private Integer currentSeason;

    @Column
    private Integer currentEpisode;
}
