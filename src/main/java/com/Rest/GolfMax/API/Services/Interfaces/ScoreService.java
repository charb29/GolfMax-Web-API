package com.Rest.GolfMax.API.Services.Interfaces;

import com.Rest.GolfMax.API.Models.Score;


import java.util.List;
import java.util.Optional;

public interface ScoreService {
    List<Score> getAllScores();
    Score createScore(Score score);
    Optional<Score>getScoreById(long id);
    void deleteScore(long id);
    List<Score> getScoresByUserId(long id);
    List<Score> getScoresByCourseId(long id);
}
