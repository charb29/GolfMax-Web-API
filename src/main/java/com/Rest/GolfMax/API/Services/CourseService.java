package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

    public List<String> listAllCourseNames() {
        return courseRepository.findAllCourseNames();
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public Course getCourseByCourseId(long courseId) {
        return courseRepository.findByCourseId(courseId);
    }
}
