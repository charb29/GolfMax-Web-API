package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.*;
import com.Rest.GolfMax.API.Repositories.PlayerStatisticsRepository;
import com.Rest.GolfMax.API.Repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class PlayerStatisticsService {

    @Autowired
    PlayerStatisticsRepository repository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    ScoreService scoreService;


    public PlayerStatistics getStatsByUserId(long id) {
        return repository.findByUserId(id);
    }

    public void saveStats(PlayerStatistics stats, long id) {
        User user = new User();
        HandicapCalculator calculator = new HandicapCalculator();
        user.setId(id);
        calculator.setHandicapIndex(scoreService.getScoresByUserId(user.getId(), Sort.unsorted()));

        int roundsPlayed = scoreService.getScoresByUserId(user.getId(), Sort.unsorted()).size();
        double averageScore = getAverageScore(getUserScores(id));
        double handicapIndex = calculator.getHandicapIndex();

        stats.setUser(user);
        stats.setRoundsPlayed(roundsPlayed);
        stats.setAverageScore(averageScore);
        stats.setHandicapIndex(handicapIndex);

        repository.save(stats);
    }


    private double getAverageScore(List<Integer> scores) {
        double sum = 0;

        for (int i : scores) {
            sum += i;
        }

        sum = sum / scores.size();

        return Math.round(sum * 10.0) / 10.0;
    }

    private List<Integer> getUserScores(long id) {
        User user = new User();
        user.setId(id);
        List<Integer> scores = scoreRepository.findByUserId(user.getId());
        return scores;
    }
}

