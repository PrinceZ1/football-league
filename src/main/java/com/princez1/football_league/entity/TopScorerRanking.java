package com.princez1.football_league.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "top_scorer_ranking")
public class TopScorerRanking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "goals", nullable = false)
    private int goals;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    // Getters and Setters
}
