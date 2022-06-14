package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.Score;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    public List<Score> findByUserId(long userId, Sort sort);

    public List<Score> findByCourseId(long courseId, Sort sort);
}
