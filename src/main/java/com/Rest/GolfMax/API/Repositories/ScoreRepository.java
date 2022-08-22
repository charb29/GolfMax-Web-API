package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.Score;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findByUserIdOrderByUserScoreAsc(long id);
    List<Score> findByCourseIdOrderByUserScoreAsc(long id);

    @Query("SELECT s.userScore FROM Score s WHERE s.user.id = :userId")
    List<Integer> findByUserId(@Param("userId") Long userId);

}