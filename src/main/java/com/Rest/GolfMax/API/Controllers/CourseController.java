package com.Rest.GolfMax.API.Controllers;
import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Repositories.CourseRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Rest.GolfMax.API.Services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
    
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseService courseService;

    @GetMapping("")
    public List<Course> listAllCourses() {
        return courseService.listAllCourses();
    }

    @PostMapping("/new_course")
    public ResponseEntity<Course> addNewCourse(@RequestBody Course course) {
        if (courseRepository.existsByCourseName(course.getCourseName())) {
            return new ResponseEntity<>(course, HttpStatus.BAD_REQUEST);
        }
        courseRepository.save(course);
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
    public void deleteUser(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/course_attributes/{id}")
    public List<String> getCourseAttributesById(@PathVariable Long id) {
        return courseService.getCourseNameAndAttributesById(id);
    }
}
