package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    public Course findByCourseId(long courseId);
    public Boolean existsByCourseName(String courseName);

    @Query("SELECT courseName FROM Course")
    public List<String> findAllCourseNames();
}
