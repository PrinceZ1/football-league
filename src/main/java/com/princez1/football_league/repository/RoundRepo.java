package com.princez1.football_league.repository;

import com.princez1.football_league.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepo extends JpaRepository<Round, Integer> {
}
