package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Services.CourseService;
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
    CourseService courseService;

    @GetMapping("")
    public List<Course> listAllCourses() {
        return courseService.listAllCourses();
    }

    @PostMapping("/new_course")
    public ResponseEntity<Course> addNewCourse(@RequestBody Course course) {
        if (courseService.existsByCourseName(course.getCourseName())) {
            return new ResponseEntity<>(course, HttpStatus.BAD_REQUEST);
        }
        courseService.saveCourse(course);
        return new ResponseEntity<Course>(course, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        try {
            Course course = courseService.getCourseById(id);
            return new ResponseEntity<Course>(course, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        courseService.deleteCourse(id);
    }
}
