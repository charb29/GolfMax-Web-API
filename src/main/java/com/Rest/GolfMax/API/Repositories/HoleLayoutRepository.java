package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.HoleLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoleLayoutRepository extends JpaRepository<HoleLayout, Long> {
}
