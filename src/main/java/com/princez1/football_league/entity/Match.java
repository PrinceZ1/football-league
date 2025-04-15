package com.princez1.football_league.entity;

import com.princez1.football_league.enums.MatchStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "match_date", nullable = false)
    private LocalDateTime matchDate;

    @Column
    private String result;

    @Column(nullable = false)
    private int attendee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchStatus status;

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

    @OneToMany(mappedBy = "match")
    private List<MatchEvent> matchEvents;

    @OneToMany(mappedBy = "match")
    private List<MatchPlayer> matchPlayers;

    @OneToMany(mappedBy = "match")
    private List<RefereeAssignment> refereeAssignments;
}

