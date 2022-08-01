package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.PlayerStatistics;
import com.Rest.GolfMax.API.Services.PlayerStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/stats")
public class PlayerStatisticsController {

    @Autowired
    PlayerStatisticsService playerStatisticsService;

    @GetMapping("/user/{id}")
    public PlayerStatistics getStatsByUserId(@PathVariable long id) {
        return playerStatisticsService.getStatsByUserId(id);
    }

    @PostMapping("{id}")
    public ResponseEntity<PlayerStatistics> saveStats(@PathVariable long id, PlayerStatistics stats) {
        playerStatisticsService.saveStats(stats, id);
        return new ResponseEntity<PlayerStatistics>(stats, HttpStatus.CREATED);
    }
}
