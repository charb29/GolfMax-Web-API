package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;


@Service
@Transactional
@RequestScope
public class CourseService {
    private final CourseRepository COURSE_REPOSITORY;

    public CourseService(CourseRepository COURSE_REPOSITORY) {
        this.COURSE_REPOSITORY = COURSE_REPOSITORY;
    }

    public List<Course> listAllCourses() {
        return COURSE_REPOSITORY.findAll();
    }

    public void addNewCourse(Course course) {
        COURSE_REPOSITORY.save(course);
    }

    public Course getCourseById(Long id) {
        return COURSE_REPOSITORY.findById(id).get();
    }
    
    public void deleteCourse(Long id) {
        COURSE_REPOSITORY.deleteById(id);
    }

    public boolean existsByCourseName(String courseName) {
        return !COURSE_REPOSITORY.existsByCourseName(courseName);
    }
}
