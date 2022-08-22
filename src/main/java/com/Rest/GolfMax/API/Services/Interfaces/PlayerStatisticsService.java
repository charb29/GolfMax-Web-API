package com.Rest.GolfMax.API.Services.Interfaces;

import com.Rest.GolfMax.API.Models.PlayerStatistics;

import java.util.List;

public interface PlayerStatisticsService {
    List<PlayerStatistics> getStatisticsByUserId(long id);
    PlayerStatistics saveUserStatistics(PlayerStatistics playerStatistics, long id);
}

