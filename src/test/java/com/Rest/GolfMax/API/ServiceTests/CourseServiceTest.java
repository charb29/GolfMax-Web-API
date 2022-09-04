package com.Rest.GolfMax.API.ServiceTests;

import com.Rest.GolfMax.API.Models.Course;

import com.Rest.GolfMax.API.Repositories.CourseRepository;
import com.Rest.GolfMax.API.Services.Implementations.CourseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    public void getAllCourses_should_return_list_size_3() {
        Course course1 = new Course();
        course1.setId(1L);
        Course course2 = new Course();
        course2.setId(2L);
        Course course3 = new Course();
        course3.setId(3L);

        List<Course> courseRequest = new ArrayList<>(Arrays.asList(course1, course2, course3));

        when(courseService.getAllCourses()).thenReturn(courseRequest);

        List<Course> courseResponse = courseService.getAllCourses();

        assertThat(courseRequest.size()).isSameAs(courseResponse.size());
        assertThat(courseResponse.size()).isEqualTo(3);
    }

    @Test
    public void createCourse_should_return_course() {
        Course courseRequest = new Course();
        courseRequest.setCourseName("Vista Valencia");

        when(courseRepository.save(courseRequest)).thenReturn(new Course());

        Course createdCourse = courseService.createCourse(courseRequest);

        assertThat(createdCourse.getCourseName()).isSameAs(courseRequest.getCourseName());
    }

    @Test
    public void getCourseById_should_return_course() {
        Course courseRequest = new Course();
        courseRequest.setId(1L);
        courseRequest.setCourseName("Vista Valencia");

        when(courseRepository.findById(courseRequest.getId())).thenReturn(Optional.of(courseRequest));

        Course courseResponse = courseService.getCourseById(courseRequest.getId());

        assertThat(courseResponse.getId()).isSameAs(courseRequest.getId());
        assertThat(courseResponse.getCourseName()).isSameAs(courseRequest.getCourseName());
    }

    @Test
    public void deleteCourse_whenCourseDeleted_thenNothing() {
        long courseId = 1;

        willDoNothing().given(courseRepository).deleteById(courseId);

        courseService.deleteCourse(courseId);

        verify(courseRepository, times(1)).deleteById(courseId);
    }
}
