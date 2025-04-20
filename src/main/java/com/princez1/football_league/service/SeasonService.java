package com.princez1.football_league.service;

import com.princez1.football_league.dto.Response;
import com.princez1.football_league.dto.SeasonDto;
import com.princez1.football_league.entity.Season;
import com.princez1.football_league.enums.SeasonStatus;
import com.princez1.football_league.exception.NotFoundException;
import com.princez1.football_league.repository.SeasonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepo seasonRepo;

    public Response addSeason(SeasonDto seasonDto) {
        Season season = new Season();
        season.setName(seasonDto.getName());
        season.setYear(seasonDto.getYear());
        season.setStartDate(seasonDto.getStartDate());
        season.setEndDate(seasonDto.getEndDate());
        season.setStatus(seasonDto.getStatus() != null ? seasonDto.getStatus() : SeasonStatus.ONGOING);

        Season saved = seasonRepo.save(season);
        seasonDto.setId(saved.getId());
        return Response.builder()
                .status(200)
                .message("Season created successfully")
                .build();
    }

    public Response updateSeason(int id, SeasonDto seasonDto) {
        Optional<Season> opt = seasonRepo.findById(seasonDto.getId());
        if (opt.isEmpty()) throw new NotFoundException("Season not found");

        Season Season = opt.get();
        Season.setName(seasonDto.getName());
        Season.setYear(seasonDto.getYear());
        Season.setStartDate(seasonDto.getStartDate());
        Season.setEndDate(seasonDto.getEndDate());
        Season.setStatus(seasonDto.getStatus());
        seasonRepo.save(Season);

        return Response.builder()
                .status(200)
                .message("Season update successfully")
                .build();
    }

    public Response deleteSeason(int id) {
        if (!seasonRepo.existsById(id)) throw new NotFoundException("Season not found");
        seasonRepo.deleteById(id);
        return Response.builder()
                .status(200)
                .message("Season deleted successfully")
                .build();
    }

    public Response getAllSeasons() {
        List<Season> list = seasonRepo.findAll();
        List<SeasonDto> dtoList = list.stream().map(s -> {
            SeasonDto dto = new SeasonDto();
            dto.setId(s.getId());
            dto.setName(s.getName());
            dto.setYear(s.getYear());
            dto.setStartDate(s.getStartDate());
            dto.setEndDate(s.getEndDate());
            dto.setStatus(s.getStatus());
            return dto;
        }).collect(Collectors.toList());
        return Response.builder()
                .status(200)
                .userList(null)
                .message("All season")
                .build();
    }
}
