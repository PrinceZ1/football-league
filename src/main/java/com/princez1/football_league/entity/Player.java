package com.princez1.football_league.entity;

import com.princez1.football_league.enums.PlayerStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String position;

    @Column(name = "jersey_number")
    private Integer jerseyNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlayerStatus status;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @OneToMany(mappedBy = "player")
    private List<GameEvent> gameEvents;

    @OneToMany(mappedBy = "player")
    private List<GamePlayer> gamePlayers;

    @OneToMany(mappedBy = "player")
    private List<TopScorerRanking> topScorerRankings;
}

