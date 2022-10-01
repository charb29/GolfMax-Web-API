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


@Service
@Transactional
@RequestScope
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final HoleLayoutRepository holeLayoutRepository;
    private final HoleRepository holeRepository;

    public CourseServiceImpl(CourseRepository courseRepository, HoleLayoutRepository holeLayoutRepository,
                             HoleRepository holeRepository) {
        super();
        this.courseRepository = courseRepository;
        this.holeLayoutRepository = holeLayoutRepository;
        this.holeRepository = holeRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll(Sort.by(Sort.Direction.ASC, "courseName"));
    }

    @Override
    public Course createCourse(Course course) {
        holeLayoutRepository.saveAll(course.getHoleLayout());
        for (int i = 0; i < course.getHoleLayout().size(); i++) {
            holeRepository.saveAll(course.getHoleLayout().get(i).getHoles());
        }
        courseRepository.save(course);
        return course;
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public boolean isValidCourse(String courseName) {
        return !courseRepository.existsByCourseName(courseName);
    }

}
