package com.princez1.football_league.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "award_payment")
public class AwardPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "award_id", nullable = false)
    private Award award;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    // Getters and Setters
}
