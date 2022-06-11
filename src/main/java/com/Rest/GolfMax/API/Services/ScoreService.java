package com.Rest.GolfMax.API.Services;
import javax.transaction.Transactional;
import com.Rest.GolfMax.API.Repositories.ScoreRepository;
import com.Rest.GolfMax.API.Models.Score;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
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

    public List<Score> getScoreByUserId(long userId, Sort sort) {
        return scoreRepository.findByUserId(userId, sort);
    }

    public List<Score> getScoreByCourseId(long courseId) {
        return scoreRepository.findByCourseId(courseId);
    }

}
