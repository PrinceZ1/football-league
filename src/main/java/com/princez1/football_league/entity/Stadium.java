package com.princez1.football_league.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "stadium")
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Column(nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "stadium")
    private List<Game> games;

    // Getters and Setters
}
