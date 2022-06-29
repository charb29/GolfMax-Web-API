package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.PlayerStatistics;
import com.Rest.GolfMax.API.Repositories.PlayerStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlayerStatisticsService {

    @Autowired
    PlayerStatisticsRepository playerStatisticsRepository;

    public PlayerStatistics getUserStats(long id) {
        return playerStatisticsRepository.findByUserId(id);
    }
}
