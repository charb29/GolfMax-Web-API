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

    private final PlayerStatisticsRepository statisticsRepository;
    private final ScoreRepository scoreRepository;
    private final ScoreServiceImpl scoreService;

    public PlayerStatisticsServiceImpl(PlayerStatisticsRepository playerStatisticsRepository,
                                       ScoreRepository scoreRepository, ScoreServiceImpl scoreService) {
        super();
        this.statisticsRepository = playerStatisticsRepository;
        this.scoreRepository = scoreRepository;
        this.scoreService = scoreService;
    }

    @Override
    public List<PlayerStatistics> getStatsByUserId(Long id) {
        return statisticsRepository.findByUserIdOrderByUserScoresAsc(id);
    }

    @Override
    public PlayerStatistics saveStats(PlayerStatistics stats, Long id) {
        User user = new User();
        HandicapCalculator calculator = new HandicapCalculator();
        user.setId(id);
        calculator.setHandicapIndex(scoreService.getAllScoresByUserId(user.getId()));

        int roundsPlayed = scoreService.getAllScoresByUserId(user.getId()).size();
        double averageScore = getAverageScore(getUserScores(id));
        double handicapIndex = calculator.getHandicapIndex();

        stats.setUser(user);
        stats.setRoundsPlayed(roundsPlayed);
        stats.setAverageScore(averageScore);
        stats.setHandicapIndex(handicapIndex);

        return statisticsRepository.save(stats);
    }


    private double getAverageScore(@NotNull List<Integer> scores) {
        double sum = 0;
        for (int i : scores) {
            sum += i;
        }
        sum /= scores.size();
        return Math.round(sum * 10.0) / 10.0;
    }

    private List<Integer> getUserScores(Long id) {
        return scoreRepository.findByUserId(id);
    }
}
