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

    private final CourseService COURSE_SERVICE;

    public CourseController(CourseService courseService) {
        super();
        this.COURSE_SERVICE = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return COURSE_SERVICE.getAllCourses();
    }

    @PostMapping("")
    public ResponseEntity<Course> addNewCourse(@RequestBody @NotNull Course courseRequest) {
        if (!COURSE_SERVICE.isValid(courseRequest.getCourseName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            Course courseResponse = COURSE_SERVICE.createCourse(courseRequest);
            return new ResponseEntity<>(courseResponse, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        try {
            Course courseResponse = COURSE_SERVICE.getCourseById(id);
            return new ResponseEntity<>(courseResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        COURSE_SERVICE.deleteCourse(id);
    }
}
