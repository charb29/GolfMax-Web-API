package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.PlayerStatistics;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.PlayerStatisticsRepository;
import com.Rest.GolfMax.API.Repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class PlayerStatisticsService {

    @Autowired
    PlayerStatisticsRepository repository;

    @Autowired
    ScoreService scoreService;

    public PlayerStatistics getStatsByUserId(long id) {
        PlayerStatistics stats = new PlayerStatistics();
        User user = new User();
        user.setId(id);

        int roundsPlayed = scoreService.getScoresByUserId(user.getId(), Sort.unsorted()).size();
        stats.setUser(user);
        stats.setRoundsPlayed(roundsPlayed);
        saveStats(stats);

        return repository.findByUserId(id);
    }

    public void saveStats(PlayerStatistics statistics) {
        repository.save(statistics);
    }
}
