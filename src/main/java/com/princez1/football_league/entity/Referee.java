package com.princez1.football_league.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "referee")
public class Referee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private int experienceYears;

    @OneToMany(mappedBy = "referee")
    private List<RefereeAssignment> refereeAssignments;

    @OneToOne(mappedBy = "referee")
    private User user;
}