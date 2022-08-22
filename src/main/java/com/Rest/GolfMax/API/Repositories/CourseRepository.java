package com.Rest.GolfMax.API.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Rest.GolfMax.API.Models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Boolean existsByCourseName(String courseName);
}
