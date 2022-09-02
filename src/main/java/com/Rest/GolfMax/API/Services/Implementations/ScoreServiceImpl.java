package com.Rest.GolfMax.API.Services.Implementations;

import com.Rest.GolfMax.API.Models.Score;
import com.Rest.GolfMax.API.Repositories.ScoreRepository;
import com.Rest.GolfMax.API.Services.Interfaces.ScoreService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequestScope
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
    public Optional<Score> getScoreById(Long id) {
        return SCORE_REPOSITORY.findById(id);
    }

    @Override
    public void deleteScore(Long id) {
        SCORE_REPOSITORY.deleteById(id);
    }

    @Override
    public List<Score> getScoresByUserId(Long id) {
        return SCORE_REPOSITORY.findByUserIdOrderByUserScoreAsc(id);
    }

    @Override
    public List<Score> getScoresByCourseId(Long id) {
        return SCORE_REPOSITORY.findByCourseIdOrderByUserScoreAsc(id);
    }
}
