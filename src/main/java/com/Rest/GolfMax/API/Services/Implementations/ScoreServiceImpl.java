package com.Rest.GolfMax.API.Services.Implementations;

import com.Rest.GolfMax.API.Models.Score;
import com.Rest.GolfMax.API.Repositories.ScoreRepository;
import com.Rest.GolfMax.API.Services.Interfaces.ScoreService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequestScope
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;

    public ScoreServiceImpl(ScoreRepository scoreRepository) {
        super();
        this.scoreRepository = scoreRepository;
    }

    @Override
    public List<Score> getAllScores() {
        return scoreRepository.findAll
                (Sort.by(Sort.Direction.ASC, "userScore"));
    }

    @Override
    public Score addScore(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public Score getScoreById(Long id) {
        return scoreRepository.findById(id).get();
    }

    @Override
    public void deleteScoreById(Long id) {
        scoreRepository.deleteById(id);
    }

    @Override
    public List<Score> getAllScoresByUserId(Long id) {
        return scoreRepository.findByUserIdOrderByUserScoreAsc(id);
    }

    @Override
    public List<Score> getAllScoresByCourseId(Long id) {
        return scoreRepository.findByCourseIdOrderByUserScoreAsc(id);
    }
}
