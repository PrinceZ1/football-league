package com.princez1.football_league.entity;

import com.princez1.football_league.enums.SeasonStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "season")
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int year;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeasonStatus status;

    @OneToMany(mappedBy = "season")
    private List<Team> teams;

    @OneToMany(mappedBy = "season")
    private List<Round> rounds;

    @OneToMany(mappedBy = "season")
    private List<Sponsorship> sponsorships;

    @OneToMany(mappedBy = "season")
    private List<Award> awards;

    @OneToMany(mappedBy = "season")
    private List<TeamRanking> teamRankings;

    @OneToMany(mappedBy = "season")
    private List<FairPlayRanking> fairPlayRankings;

    @OneToMany(mappedBy = "season")
    private List<TopScorerRanking> topScorerRankings;

    // Getters and Setters
}