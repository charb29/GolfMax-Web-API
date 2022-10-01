package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.Hole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoleRepository extends JpaRepository<Hole, Long> {
}
