package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.PlayerStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerStatisticsRepository extends JpaRepository<PlayerStatistics, Long> {

    public PlayerStatistics findByUserId(long id);
}
