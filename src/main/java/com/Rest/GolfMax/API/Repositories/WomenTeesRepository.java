package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.WomenTees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WomenTeesRepository extends JpaRepository<WomenTees, Long> {

    public WomenTees findByCourseId(long id);
}
