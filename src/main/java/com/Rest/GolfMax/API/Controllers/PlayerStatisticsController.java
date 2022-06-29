package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.PlayerStatistics;
import com.Rest.GolfMax.API.Services.PlayerStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
