package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "race")
public class Race {
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer raceID;

    @Column
    private String raceName;

    @Column
    private int baseHP;

    @Column
    private int baseMP;

    @Column
    private int baseStrength;

    @Column
    private int baseDefense;

    @Column
    private int baseSpeed;

    @Column
    private int baseAgility;

    @Column
    private int baseStamina;

    @Column
    private int baseIntelligence;

    @Column
    private int baseWisdom;
    
    // Getters and setters

}