package com.Rest.GolfMax.API.Scores;
import com.Rest.GolfMax.API.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    
    public Score findByCourseName(String courseName);

    public Score findByUserScore(int userScore);

    public Score findByCourseRating(double courseRating);

    public Score findBySlopeRating(double slopeRating);

    @Query("Select u from Score u WHERE u.user = :user")
    public Score findScoreByUser(@Param("user") Long id);
    
}
