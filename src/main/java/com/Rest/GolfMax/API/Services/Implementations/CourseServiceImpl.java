package com.Rest.GolfMax.API.Services.Implementations;

import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Repositories.CourseRepository;
import com.Rest.GolfMax.API.Services.Interfaces.CourseService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequestScope
public class CourseServiceImpl implements CourseService {

    private final CourseRepository COURSE_REPOSITORY;

    public CourseServiceImpl(CourseRepository courseRepository) {
        super();
        this.COURSE_REPOSITORY = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return COURSE_REPOSITORY.findAll(Sort.by(Sort.Direction.ASC, "courseName"));
    }

    @Override
    public Course createCourse(Course course) {
        COURSE_REPOSITORY.save(course);
        return course;
    }

    @Override
    public Optional<Course> getCourseById(long id) {
        return COURSE_REPOSITORY.findById(id);
    }

    @Override
    public void deleteCourse(long id) {
        COURSE_REPOSITORY.deleteById(id);
    }

    @Override
    public boolean isValid(String courseName) {
        if (COURSE_REPOSITORY.existsByCourseName(courseName))
            return false;
        return true;
    }

}
