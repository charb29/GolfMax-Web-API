package com.Rest.GolfMax.API.Services.Interfaces;

import com.Rest.GolfMax.API.Models.Score;


import java.util.List;


public interface ScoreService {
    List<Score> getAllScores();
    Score createScore(Score score);
    Score getScoreById(Long id);
    void deleteScore(Long id);
    List<Score> getScoresByUserId(Long id);
    List<Score> getScoresByCourseId(Long id);
}
