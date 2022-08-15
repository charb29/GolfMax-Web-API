package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.MenTeesHoleYardages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenTeesHoleYardagesRepository extends JpaRepository<MenTeesHoleYardages, Long> {

    public MenTeesHoleYardages findByCourseId(long id);
}
