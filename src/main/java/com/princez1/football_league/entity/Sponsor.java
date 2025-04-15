package com.princez1.football_league.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sponsor")
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String contactInfo;

    @OneToMany(mappedBy = "sponsor")
    private List<Sponsorship> sponsorships;

    // Getters and Setters
}

