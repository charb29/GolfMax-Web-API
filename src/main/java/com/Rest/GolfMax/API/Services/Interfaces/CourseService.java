package com.Rest.GolfMax.API.Services.Interfaces;

import com.Rest.GolfMax.API.Models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getAllCourses();
    Course createCourse(Course course);
    Optional<Course> getCourseById(long id);
    void deleteCourse(long id);
    boolean isValid(String courseName);
}
