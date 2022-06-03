package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.CourseImageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseImageRepository extends JpaRepository<CourseImageFile, Long>{
}
