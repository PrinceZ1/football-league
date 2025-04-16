package com.princez1.football_league.entity;

import com.princez1.football_league.enums.GameEventType;
import jakarta.persistence.*;

@Entity
@Table(name = "game_event")
public class GameEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameEventType type;

    @Column(nullable = false)
    private int minute;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}

