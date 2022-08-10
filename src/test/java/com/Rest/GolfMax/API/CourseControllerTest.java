package com.Rest.GolfMax.API;

import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Models.Score;
import com.Rest.GolfMax.API.Repositories.CourseRepository;
import com.Rest.GolfMax.API.Services.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(CourseControllerTest.class)
public class CourseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    CourseRepository courseRepository;

    @MockBean
    CourseService courseService;

    Course course = new Course(1,  "Elkhorn", 72.0, 131.0, 71, 4,
            3, 4, 4, 4, 5, 4, 5, 3, 4, 5, 4,
            3, 4, 4, 3, 4, 4);

    @Test
    public void addNewCourse() throws Exception {
        Mockito.when(courseRepository.save(course)).thenReturn(course);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/courses/new_course")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(course));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.courseName", is("Elkhorn")));
    }
}
