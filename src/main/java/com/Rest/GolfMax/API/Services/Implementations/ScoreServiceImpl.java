package com.Rest.GolfMax.API.Services.Implementations;

import com.Rest.GolfMax.API.Models.Score;
import com.Rest.GolfMax.API.Repositories.ScoreRepository;
import com.Rest.GolfMax.API.Services.Interfaces.ScoreService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.data.domain.Sort;

import java.util.List;

public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository SCORE_REPOSITORY;

    public ScoreServiceImpl(ScoreRepository scoreRepository) {
        super();
        this.SCORE_REPOSITORY = scoreRepository;
    }

    @Override
    public List<Score> getAllScores() {
        return SCORE_REPOSITORY.findAll
                (Sort.by(Sort.Direction.ASC, "userScore"));
    }

    @Override
    public Score createScore(Score score) {
        return SCORE_REPOSITORY.save(score);
    }

    @Override
    public Score getScoreById(long id) {
        return SCORE_REPOSITORY.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id"));
    }

    @Override
    public void deleteScore(long id) {
        SCORE_REPOSITORY.deleteById(id);
    }

    @Override
    public List<Score> getScoresByUserId(long id) {
        return SCORE_REPOSITORY.findByUserIdOrderByUserScoreAsc(id);
    }

    @Override
    public List<Score> getScoresByCourseId(long id) {
        return SCORE_REPOSITORY.findByCourseIdOrderByUserScoreAsc(id);
    }
}
