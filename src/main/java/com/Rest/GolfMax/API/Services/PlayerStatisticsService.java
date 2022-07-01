package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.HandicapCalculator;
import com.Rest.GolfMax.API.Models.PlayerStatistics;
import com.Rest.GolfMax.API.Models.Score;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.PlayerStatisticsRepository;
import com.Rest.GolfMax.API.Repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        user.setId(id);

        int roundsPlayed = scoreService.getScoresByUserId(user.getId(), Sort.unsorted()).size();
        double averageScore = getAverageScore(getUserScores(id));
        double handicap = getHandicapIndex(scoreRepository.getHandicapAttrs(user.getId()));

        stats.setUser(user);
        stats.setRoundsPlayed(roundsPlayed);
        stats.setAverageScore(averageScore);
        stats.setHandicapIndex(handicap);

        repository.save(stats);
    }

    private double getHandicapIndex(List<Score> scores) {
        HandicapCalculator calculator = new HandicapCalculator();
        calculator.setHandicapIndex(scores);
        double handicap = calculator.getHandicapIndex();
        return handicap;
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

