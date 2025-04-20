package com.princez1.football_league.controller;

import com.princez1.football_league.dto.Response;
import com.princez1.football_league.dto.RoundDto;
import com.princez1.football_league.service.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rounds")
@RequiredArgsConstructor
public class RoundController {
    private final RoundService roundService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addRound(@RequestBody RoundDto roundDto) {
        return ResponseEntity.ok(roundService.addRound(roundDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateRound(@PathVariable int id, @RequestBody RoundDto roundDto) {
        return ResponseEntity.ok(roundService.updateRound(id, roundDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteRound(@PathVariable int id) {
        return ResponseEntity.ok(roundService.deleteRound(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllRounds() {
        return ResponseEntity.ok(roundService.getAllRounds());
    }
}
