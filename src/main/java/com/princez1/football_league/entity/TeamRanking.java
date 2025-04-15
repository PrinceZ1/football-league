package com.princez1.football_league.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "team_ranking")
public class TeamRanking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int matchesPlayed;

    @Column(nullable = false)
    private int wins;

    @Column(nullable = false)
    private int draws;

    @Column(nullable = false)
    private int losses;

    @Column(name = "goals_scored", nullable = false)
    private int goalsScored;

    @Column(name = "goals_conceded", nullable = false)
    private int goalsConceded;

    @Column(name = "goal_difference", nullable = false)
    private int goalDifference;

    @Column(nullable = false)
    private int points;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    // Getters and Setters
}
