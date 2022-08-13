package com.Rest.GolfMax.API.Course;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Rest.GolfMax.API.Course.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    public List<Course> getCourseById(Long id);

    public Boolean existsByCourseName(String courseName);
}
