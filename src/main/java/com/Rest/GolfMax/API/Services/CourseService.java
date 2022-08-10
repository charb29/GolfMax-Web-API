package com.Rest.GolfMax.API.Services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Repositories.CourseRepository;
import org.springframework.web.context.annotation.RequestScope;


@Service
@Transactional
@RequestScope
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

    public void addNewCourse(Course course) {
        courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }
    
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public boolean existsByCourseName(String courseName) {
        if (courseRepository.existsByCourseName(courseName)) {
            return false;
        }
        else {
            return true;
        }
    }
}
