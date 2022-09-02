package com.Rest.GolfMax.API.Services.Interfaces;

import com.Rest.GolfMax.API.Models.PlayerStatistics;

import java.util.List;

public interface PlayerStatisticsService {
    List<PlayerStatistics> getStatisticsByUserId(Long id);
    PlayerStatistics saveUserStatistics(PlayerStatistics playerStatistics, Long id);
}

