package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.DTOs.CourseDto;
import com.Rest.GolfMax.API.Models.*;
import com.Rest.GolfMax.API.Services.Interfaces.CourseService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private ModelMapper modelMapper;
    private final CourseService COURSE_SERVICE;

    public CourseController(CourseService courseService) {
        super();
        this.COURSE_SERVICE = courseService;
    }

    @GetMapping
    public List<CourseDto> getAllCourses() {
        return COURSE_SERVICE.getAllCourses()
                .stream()
                .map(post -> modelMapper.map(post, CourseDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public ResponseEntity<CourseDto> addNewCourse(@RequestBody @NotNull CourseDto courseDto) {
        Course courseRequest = modelMapper.map(courseDto, Course.class);

        if (COURSE_SERVICE.isValid(courseDto.getCourseName()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Course course = COURSE_SERVICE.createCourse(courseRequest);
        CourseDto courseResponse = modelMapper.map(course, CourseDto.class);
        return new ResponseEntity<>(courseResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable long id) {
        try {
            Course course = COURSE_SERVICE.getCourseById(id);
            CourseDto courseResponse = modelMapper.map(course, CourseDto.class);
            return new ResponseEntity<>(courseResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable long id) {
        COURSE_SERVICE.deleteCourse(id);
    }
}
