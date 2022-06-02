package com.Rest.GolfMax.API.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Rest.GolfMax.API.Models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    public List<Course> getCourseById(Long id);

    public Boolean existsByCourseName(String courseName);

    @Query("select course.courseName, course.courseRating, course.slopeRating, course.overallPar from Course course where course.id = :id")
    public List<String> getCourseNameAndRatingsById(@Param("id") Long id);

}
