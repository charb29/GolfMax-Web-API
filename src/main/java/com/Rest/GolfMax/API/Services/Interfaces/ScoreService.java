package com.Rest.GolfMax.API.Services.Interfaces;

import com.Rest.GolfMax.API.Models.Score;


import java.util.List;


public interface ScoreService {
    List<Score> getAllScores();
    Score addScore(Score score);
    Score getScoreById(Long id);
    void deleteScoreById(Long id);
    List<Score> getAllScoresByUserId(Long id);
    List<Score> getAllScoresByCourseId(Long id);
}
