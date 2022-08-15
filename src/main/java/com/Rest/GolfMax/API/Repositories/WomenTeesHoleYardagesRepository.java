package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.WomenTeesHoleYardages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WomenTeesHoleYardagesRepository extends JpaRepository<WomenTeesHoleYardages, Long> {

    public WomenTeesHoleYardages findByCourseId(long id);
}
