package com.Rest.GolfMax.API.Services.Interfaces;

import com.Rest.GolfMax.API.Models.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();
    Course createCourse(Course course);
    Course getCourseById(long id);
    void deleteCourse(long id);
    boolean isValid(String courseName);
}
