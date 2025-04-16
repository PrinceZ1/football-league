package com.princez1.football_league.entity;

import com.princez1.football_league.enums.GameStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "game", indexes = {
        @Index(name = "idx_game_round", columnList = "roundId"),
        @Index(name = "idx_game_teams", columnList = "homeTeamId, awayTeamId"),
        @Index(name = "idx_game_date", columnList = "gameDate")
})
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDateTime gameDate;

    @Column(nullable = false)
    private int homeTeamGoals = 0;

    @Column(nullable = false)
    private int awayTeamGoals = 0;

    @Column(nullable = false)
    private int attendees = 0;

    @Column(nullable = false)
    private LocalDateTime registrationDeadline;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameStatus status;

    @Column
    private String cancellationReason;

    @ManyToOne
    @JoinColumn(name = "stadiumId", nullable = false)
    private Stadium stadium;

    @ManyToOne
    @JoinColumn(name = "homeTeamId", nullable = false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "awayTeamId", nullable = false)
    private Team awayTeam;

    @ManyToOne
    @JoinColumn(name = "roundId", nullable = false)
    private Round round;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}

