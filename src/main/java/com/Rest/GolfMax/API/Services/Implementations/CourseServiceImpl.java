package com.Rest.GolfMax.API.Services.Implementations;

import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Repositories.CourseRepository;
import com.Rest.GolfMax.API.Repositories.HoleLayoutRepository;
import com.Rest.GolfMax.API.Repositories.HoleRepository;
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
    private final HoleLayoutRepository HOLE_LAYOUT_REPOSITORY;
    private final HoleRepository HOLE_REPOSITORY;

    public CourseServiceImpl(CourseRepository courseRepository, HoleLayoutRepository holeLayoutRepository,
                             HoleRepository holeRepository) {
        super();
        this.COURSE_REPOSITORY = courseRepository;
        this.HOLE_LAYOUT_REPOSITORY = holeLayoutRepository;
        this.HOLE_REPOSITORY = holeRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return COURSE_REPOSITORY.findAll(Sort.by(Sort.Direction.ASC, "courseName"));
    }

    @Override
    public Course createCourse(Course course) {
        HOLE_LAYOUT_REPOSITORY.saveAll(course.getHoleLayout());
        for (int i = 0; i < course.getHoleLayout().size(); i++) {
            HOLE_REPOSITORY.saveAll(course.getHoleLayout().get(i).getHoles());
        }
        COURSE_REPOSITORY.save(course);
        return course;
    }

    @Override
    public Course getCourseById(Long id) {
        return COURSE_REPOSITORY.findById(id).get();
    }

    @Override
    public void deleteCourse(Long id) {
        COURSE_REPOSITORY.deleteById(id);
    }

    @Override
    public boolean isValid(String courseName) {
        if (COURSE_REPOSITORY.existsByCourseName(courseName))
            return false;
        return true;
    }

}
