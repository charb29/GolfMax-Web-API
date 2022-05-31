package com.Rest.GolfMax.API.Scores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    
    public Score findByCourseName(String courseName);

    public Score findByUserScore(int userScore);

    public Score findByCourseRating(double courseRating);

    public Score findBySlopeRating(double slopeRating);

    public List<Score> findByUserId(long userId);
    
}
