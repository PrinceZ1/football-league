package com.princez1.football_league.controller;

import com.princez1.football_league.dto.Response;
import com.princez1.football_league.dto.SeasonDto;
import com.princez1.football_league.service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/season")
@RequiredArgsConstructor
public class SeasonController {
    private final SeasonService seasonService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addSeason(@RequestBody SeasonDto seasonDto) {
        return ResponseEntity.ok(seasonService.addSeason(seasonDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateSeason(@PathVariable int id, @RequestBody SeasonDto seasonDto) {
        return ResponseEntity.ok(seasonService.updateSeason(id, seasonDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteSeason(@PathVariable int id) {
        return ResponseEntity.ok(seasonService.deleteSeason(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllSeasons() {
        return ResponseEntity.ok(seasonService.getAllSeasons());
    }

}
