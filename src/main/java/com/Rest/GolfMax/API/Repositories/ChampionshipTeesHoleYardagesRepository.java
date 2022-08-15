package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.ChampionshipTeesHoleYardages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionshipTeesHoleYardagesRepository extends JpaRepository<ChampionshipTeesHoleYardages, Long> {

    public ChampionshipTeesHoleYardages findByCourseId(long courseId);
}
