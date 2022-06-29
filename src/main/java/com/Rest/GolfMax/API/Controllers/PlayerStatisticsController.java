package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.PlayerStatistics;
import com.Rest.GolfMax.API.Services.PlayerStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/stats")
public class PlayerStatisticsController {

    @Autowired
    PlayerStatisticsService playerStatisticsService;

    @GetMapping("/user/{id}")
    public PlayerStatistics getUserStats(@PathVariable long id) {
        return playerStatisticsService.getUserStats(id);
    }
}
