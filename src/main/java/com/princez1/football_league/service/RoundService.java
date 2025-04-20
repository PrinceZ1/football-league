package com.princez1.football_league.service;

import com.princez1.football_league.dto.Response;
import com.princez1.football_league.dto.RoundDto;
import com.princez1.football_league.entity.Round;
import com.princez1.football_league.entity.Season;
import com.princez1.football_league.exception.NotFoundException;
import com.princez1.football_league.repository.RoundRepo;
import com.princez1.football_league.repository.SeasonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoundService {
    private final RoundRepo roundRepo;
    public final SeasonRepo seasonRepo;

    public Response addRound(RoundDto roundDto) {
        Season season = seasonRepo.findById(roundDto.getSeason_id())
                .orElseThrow(() -> new NotFoundException("Season not found"));

        Round round = new Round();
        round.setName(roundDto.getName());
        round.setSeason(season);

        Round saved = roundRepo.save(round);
        roundDto.setId(saved.getId());

        return Response.builder()
                .status(200)
                .message("Round created successfully")
                .build();
    }

    public Response updateRound(int id, RoundDto roundDto) {
        Round round = roundRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Round not found"));
        Season season = seasonRepo.findById(roundDto.getSeason_id())
                        .orElseThrow(() -> new NotFoundException("Season not found"));

        round.setName(roundDto.getName());
        round.setSeason(season);
        roundRepo.save(round);

        return Response.builder()
                .status(200)
                .message("Round update successfully")
                .build();
    }

    public Response deleteRound(int id) {
        if (!roundRepo.existsById(id)) throw new NotFoundException("Round not found");
        roundRepo.deleteById(id);
        return Response.builder()
                .status(200)
                .message("Round deleted successfully")
                .build();
    }

    public Response getAllRounds() {
        List<Round> list = roundRepo.findAll();
        List<RoundDto> dtoList = list.stream().map(r -> {
            RoundDto dto = new RoundDto();
            dto.setId(r.getId());
            dto.setName(r.getName());
            dto.setSeason_id(r.getSeason().getId());
            dto.setCreatedAt(LocalDate.from(r.getCreatedAt()));
            dto.setUpdatedAt(LocalDate.from(r.getUpdatedAt()));
            return dto;
        }).collect(Collectors.toList());
        return Response.builder()
                .status(200)
                .message("All rounds")
                .userList(null)
                .build();
    }
}
