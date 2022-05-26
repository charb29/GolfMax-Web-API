package com.Rest.GolfMax.API.Scores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    
    public Score findByCourseName(String courseName);

    public Score findByUserScore(int userScore);

    public Score findByCourseRating(double courseRating);

    public Score findBySlopeRating(double slopeRating);
    
}
