package com.princez1.football_league.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "sponsorship")
public class Sponsorship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double amount;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "sponsor_id", nullable = false)
    private Sponsor sponsor;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    // Getters and Setters
}