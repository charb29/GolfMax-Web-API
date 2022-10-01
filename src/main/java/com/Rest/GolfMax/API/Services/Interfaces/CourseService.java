package com.Rest.GolfMax.API.Services.Interfaces;

import com.Rest.GolfMax.API.Models.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();
    Course createCourse(Course course);
    Course getCourseById(Long id);
    void deleteCourseById(Long id);
    boolean isValidCourse(String courseName);
}
