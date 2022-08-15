package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.MenTees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenTeesRepository extends JpaRepository<MenTees, Long> {

    public MenTees findByCourseId(long courseId);
}
