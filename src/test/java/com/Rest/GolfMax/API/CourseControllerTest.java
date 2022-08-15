package com.Rest.GolfMax.API;

import com.Rest.GolfMax.API.Controllers.CourseController;
import com.Rest.GolfMax.API.Models.ChampionshipTees;
import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Models.MenTees;
import com.Rest.GolfMax.API.Models.WomenTees;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CourseService service;
    @MockBean
    private CourseRepository repository;

    private final ChampionshipTees CHAMPIONSHIP_TEES = new ChampionshipTees(1, 65.4, 113,
            4, 3, 3, 4, 3, 3, 4, 3, 4, 4, 3,
            3, 3, 3, 4, 4, 3, 3, 357, 213,
            190, 371, 103, 166, 316, 139, 371,
            439, 182, 71, 82, 121, 332, 404,
            156, 179, 2226, 1966, 61);
    private final MenTees MENS_TEES = new MenTees(1, 63.6, 107, 4, 3, 3,
            4, 3, 3, 4, 3, 4, 4, 3, 3, 3, 3,
            4, 4, 3, 3, 336, 202, 179, 365,
            85, 141, 305, 129, 360, 454, 166,
            71, 74, 117, 311, 389, 145, 169,
            2102, 1866, 61);
    private final WomenTees WOMEN_TEES = new WomenTees(1, 62.1, 104, 4, 4, 3,
            4, 3, 3, 4, 3, 5, 5, 3, 3, 3, 3,
            4, 4, 3, 3, 312, 184, 169, 356, 68,
            124, 288, 114, 340, 393, 147, 71,
            73, 117, 277, 375, 112, 161,
            1955, 1726, 64);
    private final Course COURSE = new Course(1, CHAMPIONSHIP_TEES, MENS_TEES, WOMEN_TEES,
            "Vista Valencia Golf Course");

    @Test
    public void listAllCourses() throws Exception {
        List<Course> courseList = new ArrayList<>(Arrays.asList(COURSE));

        Mockito.when(service.listAllCourses()).thenReturn(courseList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/course")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getCourseById() throws Exception {
        Mockito.when(service.getCourseById(COURSE.getId())).thenReturn(COURSE);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/courses/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void deleteCourse() throws Exception {
        Mockito.when(service.getCourseById(COURSE.getId())).thenReturn(COURSE);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/courses/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void saveCourse() throws Exception {
        Mockito.when(repository.save(COURSE)).thenReturn(COURSE);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/courses/new-course")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(COURSE));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.courseName", is("Vista Valencia Golf Course")));
    }
}
