package com.princez1.football_league.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fair_play_ranking")
public class FairPlayRanking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "yellow_cards", nullable = false)
    private int yellowCards;

    @Column(name = "red_cards", nullable = false)
    private int redCards;

    @Column(nullable = false)
    private int fouls;

    @Column(name = "fair_play_points", nullable = false)
    private double fairPlayPoints;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    // Getters and Setters
}
