package com.Rest.GolfMax.API.Score;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    public List<Score> findByUserId(long userId, Sort sort);

    public List<Score> findByCourseId(long courseId, Sort sort);

    @Query("SELECT s.userScore FROM Score s WHERE s.user.id = :userId")
    public List<Integer> findByUserId(@Param("userId") Long userId);

}