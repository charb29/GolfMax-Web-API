package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    public List<Score> findByUserId(long userId);

    
}
