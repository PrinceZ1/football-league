package com.princez1.football_league.entity;

import com.princez1.football_league.enums.RefereeRole;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "referee_assignment", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"gameId", "role"})
})
@Data
public class RefereeAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RefereeRole role;

    @ManyToOne
    @JoinColumn(name = "gameId", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "refereeId", nullable = false)
    private Referee referee;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
