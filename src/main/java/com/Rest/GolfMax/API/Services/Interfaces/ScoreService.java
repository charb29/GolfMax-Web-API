package com.Rest.GolfMax.API.Services.Interfaces;

import com.Rest.GolfMax.API.Models.Score;


import java.util.List;
import java.util.Optional;

public interface ScoreService {
    List<Score> getAllScores();
    Score createScore(Score score);
    Optional<Score>getScoreById(Long id);
    void deleteScore(Long id);
    List<Score> getScoresByUserId(Long id);
    List<Score> getScoresByCourseId(Long id);
}
