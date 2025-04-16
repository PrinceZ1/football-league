package com.princez1.football_league.entity;

import com.princez1.football_league.enums.GameEventType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_event", indexes = {
        @Index(name = "idx_game_event_game", columnList = "gameId")
})
@Data
public class GameEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private GameEventType type;

    @Column(nullable = false)
    private int minute;

    private String description;

    @Column(columnDefinition = "JSON")
    private String details;

    @ManyToOne
    @JoinColumn(name = "gameId", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "assistingPlayerId")
    private Player assistingPlayer;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}