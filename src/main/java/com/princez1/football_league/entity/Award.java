package com.princez1.football_league.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "award")
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double value;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @OneToMany(mappedBy = "award")
    private List<AwardPayment> awardPayments;

    // Getters and Setters
}
