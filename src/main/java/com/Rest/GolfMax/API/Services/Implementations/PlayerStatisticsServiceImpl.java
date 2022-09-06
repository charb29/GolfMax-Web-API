package com.Rest.GolfMax.API.Services.Implementations;

import com.Rest.GolfMax.API.Models.HandicapCalculator;
import com.Rest.GolfMax.API.Models.PlayerStatistics;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.PlayerStatisticsRepository;
import com.Rest.GolfMax.API.Repositories.ScoreRepository;
import com.Rest.GolfMax.API.Services.Interfaces.PlayerStatisticsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequestScope
public class PlayerStatisticsServiceImpl implements PlayerStatisticsService {

    private final PlayerStatisticsRepository STATS_REPOSITORY;
    private final ScoreRepository SCORE_REPOSITORY;
    private final ScoreServiceImpl SCORE_SERVICE;

    public PlayerStatisticsServiceImpl(PlayerStatisticsRepository playerStatisticsRepository,
                                       ScoreRepository scoreRepository, ScoreServiceImpl scoreService) {
        super();
        this.STATS_REPOSITORY = playerStatisticsRepository;
        this.SCORE_REPOSITORY = scoreRepository;
        this.SCORE_SERVICE = scoreService;
    }

    @Override
    public List<PlayerStatistics> getStatisticsByUserId(Long id) {
        return STATS_REPOSITORY.findByUserIdOrderByUserScoresAsc(id);
    }

    @Override
    public PlayerStatistics saveUserStatistics(PlayerStatistics stats, Long id) {
        User user = new User();
        HandicapCalculator calculator = new HandicapCalculator();
        user.setId(id);
        calculator.setHandicapIndex(SCORE_SERVICE.getScoresByUserId(user.getId()));

        int roundsPlayed = SCORE_SERVICE.getScoresByUserId(user.getId()).size();
        double averageScore = getAverageScore(getUserScores(id));
        double handicapIndex = calculator.getHandicapIndex();

        stats.setUser(user);
        stats.setRoundsPlayed(roundsPlayed);
        stats.setAverageScore(averageScore);
        stats.setHandicapIndex(handicapIndex);

        return STATS_REPOSITORY.save(stats);
    }


    private double getAverageScore(@NotNull List<Integer> scores) {
        double sum = 0;
        for (int i : scores) {
            sum += i;
        }
        sum = sum / scores.size();
        return Math.round(sum * 10.0) / 10.0;
    }

    private List<Integer> getUserScores(Long id) {
        return SCORE_REPOSITORY.findByUserId(id);
    }
}
