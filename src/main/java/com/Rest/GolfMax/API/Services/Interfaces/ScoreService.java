package com.Rest.GolfMax.API.Services.Interfaces;

import com.Rest.GolfMax.API.Models.Score;


import java.util.List;

public interface ScoreService {
    List<Score> getAllScores();
    Score createScore(Score score);
    Score getScoreById(long id);
    void deleteScore(long id);
    List<Score> getScoresByUserId(long id);
    List<Score> getScoresByCourseId(long id);
}
