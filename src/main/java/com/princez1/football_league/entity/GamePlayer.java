package com.princez1.football_league.entity;

import com.princez1.football_league.enums.GamePlayerRole;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_player", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"gameId", "playerId"})
})
@Data
public class GamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GamePlayerRole role;

    @ManyToOne
    @JoinColumn(name = "gameId", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "playerId", nullable = false)
    private Player player;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
