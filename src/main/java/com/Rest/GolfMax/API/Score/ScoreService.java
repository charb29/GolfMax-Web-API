package com.Rest.GolfMax.API.Score;
import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Service
@Transactional
@RequestScope
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> listAllScores() {
        return scoreRepository.findAll();
    }
    
    public void saveScore(Score score) {
        scoreRepository.save(score);
    }

    public Score getScoreById(Long id) {
        return scoreRepository.findById(id).get();
    }

    public void deleteScore(Long id) {
        scoreRepository.deleteById(id);
    }

    public List<Score> getScoresByUserId(long userId, Sort sort) {
        return scoreRepository.findByUserId(userId, sort);
    }

    public List<Score> getScoreByCourseId(long courseId, Sort sort) {
        return scoreRepository.findByCourseId(courseId, sort);
    }
}
