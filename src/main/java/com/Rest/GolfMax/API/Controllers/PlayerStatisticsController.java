package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.PlayerStatistics;
import com.Rest.GolfMax.API.Services.Interfaces.PlayerStatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/player-statistics")
public class PlayerStatisticsController {

    private final PlayerStatisticsService STATS_SERVICE;

    public PlayerStatisticsController(PlayerStatisticsService playerStatisticsService) {
        super();
        this.STATS_SERVICE = playerStatisticsService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<PlayerStatistics>> getStatsByUserId(@PathVariable Long id) {
        try {
            List<PlayerStatistics> statsResponse = STATS_SERVICE.getStatisticsByUserId(id);
            return new ResponseEntity<>(statsResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<PlayerStatistics> saveStats(@PathVariable Long id, PlayerStatistics statsRequest) {
        PlayerStatistics statsResponse = STATS_SERVICE.saveUserStatistics(statsRequest, id);
        return new ResponseEntity<>(statsResponse, HttpStatus.OK);
    }
}
