package com.princez1.football_league.entity;

import com.princez1.football_league.enums.RefereeRole;
import jakarta.persistence.*;

@Entity
@Table(name = "referee_assignment")
public class RefereeAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RefereeRole role;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "referee_id", nullable = false)
    private Referee referee;

    // Getters and Setters
}
