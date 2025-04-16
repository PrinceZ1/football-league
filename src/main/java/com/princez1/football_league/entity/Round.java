package com.princez1.football_league.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "round")
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @OneToMany(mappedBy = "round")
    private List<Game> games;

    // Getters and Setters
}
