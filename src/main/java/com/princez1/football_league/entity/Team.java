package com.princez1.football_league.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @OneToMany(mappedBy = "homeTeam")
    private List<Match> homeMatches;

    @OneToMany(mappedBy = "awayTeam")
    private List<Match> awayMatches;

    @OneToMany(mappedBy = "team")
    private List<TeamRanking> teamRankings;

    @OneToMany(mappedBy = "team")
    private List<FairPlayRanking> fairPlayRankings;

    @OneToOne(mappedBy = "team")
    private User coach;

    // Getters and Setters
}

