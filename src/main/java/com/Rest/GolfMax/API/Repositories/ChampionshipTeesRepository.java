package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.ChampionshipTees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionshipTeesRepository extends JpaRepository<ChampionshipTees, Long> {

    public ChampionshipTees findByCourseId(long courseId);
}
