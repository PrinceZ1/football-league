package com.princez1.football_league.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "fair_play_ranking", indexes = {
        @Index(name = "idx_fair_play_ranking_season", columnList = "season_id")
})
@Data
public class FairPlayRanking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int yellowCards;

    @Column(nullable = false)
    private int redCards;

    @Column(nullable = false)
    private int fouls;

    @Column(nullable = false)
    private double fairPlayPoints;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
