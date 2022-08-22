package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.DTOs.PlayerStatisticsDto;
import com.Rest.GolfMax.API.Models.PlayerStatistics;
import com.Rest.GolfMax.API.Services.Interfaces.PlayerStatisticsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/player-statistics")
public class PlayerStatisticsController {
    @Autowired
    private ModelMapper modelMapper;
    private final PlayerStatisticsService STATS_SERVICE;

    public PlayerStatisticsController(PlayerStatisticsService playerStatisticsService) {
        super();
        this.STATS_SERVICE = playerStatisticsService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<PlayerStatisticsDto>> getStatsByUserId(@PathVariable long id) {
        try {
            List<PlayerStatistics> stats = STATS_SERVICE.getStatisticsByUserId(id);
            PlayerStatisticsDto statsResponse = modelMapper.map(stats, PlayerStatisticsDto.class);
            return new ResponseEntity<>(Arrays.asList(statsResponse), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<PlayerStatisticsDto> saveStats(@PathVariable long id, PlayerStatisticsDto statsDto) {
        PlayerStatistics statsRequest = modelMapper.map(statsDto, PlayerStatistics.class);
        PlayerStatistics stats = STATS_SERVICE.saveUserStatistics(statsRequest, id);
        PlayerStatisticsDto statsResponse = modelMapper.map(stats, PlayerStatisticsDto.class);
        return new ResponseEntity<>(statsResponse, HttpStatus.CREATED);
    }
}
