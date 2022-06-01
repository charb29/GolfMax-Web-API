package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Models.Score;
import com.Rest.GolfMax.API.Repositories.CourseRepository;
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

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("")
    public List<Course> getAllCourses() {
        return courseService.listAllCourses();
    }

    @GetMapping("/course-names")
    public List<String> getAllCourseNames() {
        return courseService.listAllCourseNames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        try {
            Course course = courseService.getCourseByCourseId(id);
            return new ResponseEntity<Course> (course, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Course> (HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        if (courseRepository.existsByCourseName(course.getCourseName())) {
            return new ResponseEntity<>(course, HttpStatus.BAD_REQUEST);
        }
        courseRepository.save(course);
        return new ResponseEntity<Course>(course, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
