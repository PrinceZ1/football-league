package com.princez1.football_league.repository;

import com.princez1.football_league.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepo extends JpaRepository<Season, Integer> {
}
