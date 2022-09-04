package com.Rest.GolfMax.API.Services.Interfaces;

import com.Rest.GolfMax.API.Models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getAllCourses();
    Course createCourse(Course course);
    Course getCourseById(Long id);
    void deleteCourse(Long id);
    boolean isValid(String courseName);
}
