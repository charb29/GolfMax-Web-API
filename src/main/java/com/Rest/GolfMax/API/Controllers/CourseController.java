package com.Rest.GolfMax.API.Controllers;


import com.Rest.GolfMax.API.Models.*;
import com.Rest.GolfMax.API.Services.Interfaces.CourseService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        super();
        if (courseService == null) {
            throw new NullPointerException("Course Service");
        }
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        try {
            List<Course> courseResponse = courseService.getAllCourses();
            return new ResponseEntity<>(courseResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Course> addNewCourse(@RequestBody @NotNull Course courseRequest) {
        if (courseService.isValidCourse(courseRequest.getCourseName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            Course courseResponse = courseService.createCourse(courseRequest);
            return new ResponseEntity<>(courseResponse, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseByCourseId(@PathVariable Long id) {
        try {
            Course courseResponse = courseService.getCourseById(id);
            return new ResponseEntity<>(courseResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourseById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
