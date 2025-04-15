package com.princez1.football_league.entity;

import com.princez1.football_league.enums.UserRole;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; // Will be hashed (e.g., BCrypt)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column
    private String fullName;

    @Column(unique = true)
    private String email;

    @Column
    private String phone;

    @OneToOne
    @JoinColumn(name = "team_id", unique = true)
    private Team team; // For COACH role

    @OneToOne
    @JoinColumn(name = "referee_id", unique = true)
    private Referee referee; // For REFEREE role

}