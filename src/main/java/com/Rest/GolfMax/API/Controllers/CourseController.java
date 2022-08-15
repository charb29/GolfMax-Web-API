package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Services.CourseService;
import com.Rest.GolfMax.API.Models.Course;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public List<Course> listAllCourses() {
        return courseService.listAllCourses();
    }

    @PostMapping("/new-course")
    public ResponseEntity<Course> addNewCourse(@RequestBody @NotNull Course course) {
        if (courseService.existsByCourseName(course.getCourseName()))
            return new ResponseEntity<>(course, HttpStatus.BAD_REQUEST);
        courseService.addNewCourse(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        try {
            Course course = courseService.getCourseById(id);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        courseService.deleteCourse(id);
    }
}
