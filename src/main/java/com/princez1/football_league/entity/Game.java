package com.princez1.football_league.entity;

import com.princez1.football_league.enums.GameStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_date", nullable = false)
    private LocalDateTime gameDate;

    @Column
    private String result;

    @Column(nullable = false)
    private int attendee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameStatus status;

    @ManyToOne
    @JoinColumn(name = "stadium_id", nullable = false)
    private Stadium stadium;

    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    @ManyToOne
    @JoinColumn(name = "round_id", nullable = false)
    private Round round;

    @OneToMany(mappedBy = "game")
    private List<GameEvent> gameEvents;

    @OneToMany(mappedBy = "game")
    private List<GamePlayer> gamePlayers;

    @OneToMany(mappedBy = "game")
    private List<RefereeAssignment> refereeAssignments;
}

