package com.Rest.GolfMax.API.ControllerTests;

import com.Rest.GolfMax.API.Controllers.CourseController;
import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Models.Hole;
import com.Rest.GolfMax.API.Models.HoleLayout;
import com.Rest.GolfMax.API.Models.LayoutType;
import com.Rest.GolfMax.API.Services.Interfaces.CourseService;
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

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CourseService courseService;


    private final Course COURSE = new Course();
    private final Hole CHAMPIONSHIP_HOLE1 = new Hole(1, 357, 4);
    private final Hole CHAMPIONSHIP_HOLE2 = new Hole(2, 213, 190);
    private final Hole CHAMPIONSHIP_HOLE3 = new Hole(3, 190, 3);
    private final Hole CHAMPIONSHIP_HOLE4 = new Hole(4, 371, 4);
    private final Hole CHAMPIONSHIP_HOLE5 = new Hole(5, 103, 3);
    private final Hole CHAMPIONSHIP_HOLE6 = new Hole(6, 166, 3);
    private final Hole CHAMPIONSHIP_HOLE7 = new Hole(7, 316, 4);
    private final Hole CHAMPIONSHIP_HOLE8 = new Hole(8, 139, 3);
    private final Hole CHAMPIONSHIP_HOLE9 = new Hole(9, 371, 4);
    private final Hole CHAMPIONSHIP_HOLE10 = new Hole(10, 439, 4);
    private final Hole CHAMPIONSHIP_HOLE11 = new Hole(11, 182, 3);
    private final Hole CHAMPIONSHIP_HOLE12 = new Hole(12, 71, 3);
    private final Hole CHAMPIONSHIP_HOLE13 = new Hole(13, 82, 3);
    private final Hole CHAMPIONSHIP_HOLE14 = new Hole(14, 121, 3);
    private final Hole CHAMPIONSHIP_HOLE15 = new Hole(15, 332, 4);
    private final Hole CHAMPIONSHIP_HOLE16 = new Hole(16, 404, 4);
    private final Hole CHAMPIONSHIP_HOLE17 = new Hole(17, 456, 3);
    private final Hole CHAMPIONSHIP_HOLE18 = new Hole(19, 179, 3);

    public List<Hole> getChampionshipHoles() {
        List<Hole> holes = new ArrayList<>();
        holes.add(CHAMPIONSHIP_HOLE1);
        holes.add(CHAMPIONSHIP_HOLE2);
        holes.add(CHAMPIONSHIP_HOLE3);
        holes.add(CHAMPIONSHIP_HOLE4);
        holes.add(CHAMPIONSHIP_HOLE5);
        holes.add(CHAMPIONSHIP_HOLE6);
        holes.add(CHAMPIONSHIP_HOLE7);
        holes.add(CHAMPIONSHIP_HOLE8);
        holes.add(CHAMPIONSHIP_HOLE9);
        holes.add(CHAMPIONSHIP_HOLE10);
        holes.add(CHAMPIONSHIP_HOLE11);
        holes.add(CHAMPIONSHIP_HOLE12);
        holes.add(CHAMPIONSHIP_HOLE13);
        holes.add(CHAMPIONSHIP_HOLE14);
        holes.add(CHAMPIONSHIP_HOLE15);
        holes.add(CHAMPIONSHIP_HOLE16);
        holes.add(CHAMPIONSHIP_HOLE17);
        holes.add(CHAMPIONSHIP_HOLE18);
        return holes;
    }

    private final HoleLayout CHAMPIONSHIP_LAYOUT = new HoleLayout(1L, getChampionshipHoles(), COURSE,
            LayoutType.CHAMPIONSHIP, 2226, 1966, 61, 65.4, 113);

    private final Hole MEN_HOLE1 = new Hole(1, 336, 4);
    private final Hole MEN_HOLE2 = new Hole(2, 202, 3);
    private final Hole MEN_HOLE3 = new Hole(3, 179, 3);
    private final Hole MEN_HOLE4 = new Hole(4, 365, 4);
    private final Hole MEN_HOLE5 = new Hole(5, 85, 3);
    private final Hole MEN_HOLE6 = new Hole(6, 141, 3);
    private final Hole MEN_HOLE7 = new Hole(7, 305, 4);
    private final Hole MEN_HOLE8 = new Hole(8, 129, 3);
    private final Hole MEN_HOLE9 = new Hole(9, 360, 4);
    private final Hole MEN_HOLE10 = new Hole(10, 424, 4);
    private final Hole MEN_HOLE11 = new Hole(11, 166, 3);
    private final Hole MEN_HOLE12 = new Hole(12, 71, 3);
    private final Hole MEN_HOLE13 = new Hole(13, 74, 3);
    private final Hole MEN_HOLE14 = new Hole(14, 117, 3);
    private final Hole MEN_HOLE15 = new Hole(15, 311, 4);
    private final Hole MEN_HOLE16 = new Hole(16, 389, 4);
    private final Hole MEN_HOLE17 = new Hole(17, 145, 3);
    private final Hole MEN_HOLE18 = new Hole(19, 169, 3);

    public List<Hole> getMensHoles() {
        List<Hole> holes = new ArrayList<>();
        holes.add(MEN_HOLE1);
        holes.add(MEN_HOLE2);
        holes.add(MEN_HOLE3);
        holes.add(MEN_HOLE4);
        holes.add(MEN_HOLE5);
        holes.add(MEN_HOLE6);
        holes.add(MEN_HOLE7);
        holes.add(MEN_HOLE8);
        holes.add(MEN_HOLE9);
        holes.add(MEN_HOLE10);
        holes.add(MEN_HOLE11);
        holes.add(MEN_HOLE12);
        holes.add(MEN_HOLE13);
        holes.add(MEN_HOLE14);
        holes.add(MEN_HOLE15);
        holes.add(MEN_HOLE16);
        holes.add(MEN_HOLE17);
        holes.add(MEN_HOLE18);
        return holes;
    }

    private final HoleLayout MENS_LAYOUT = new HoleLayout(2L, getMensHoles(), COURSE,
            LayoutType.MEN, 2102, 1866, 61, 63.6, 107);

    private final Hole WOMEN_HOLE1 = new Hole(1, 312, 4);
    private final Hole WOMEN_HOLE2 = new Hole(2, 184, 4);
    private final Hole WOMEN_HOLE3 = new Hole(3, 169, 3);
    private final Hole WOMEN_HOLE4 = new Hole(4, 356, 4);
    private final Hole WOMEN_HOLE5 = new Hole(5, 68, 3);
    private final Hole WOMEN_HOLE6 = new Hole(6, 124, 3);
    private final Hole WOMEN_HOLE7 = new Hole(7, 288, 4);
    private final Hole WOMEN_HOLE8 = new Hole(8, 114, 3);
    private final Hole WOMEN_HOLE9 = new Hole(9, 340, 5);
    private final Hole WOMEN_HOLE10 = new Hole(10, 393, 5);
    private final Hole WOMEN_HOLE11 = new Hole(11, 147, 3);
    private final Hole WOMEN_HOLE12 = new Hole(12, 71, 3);
    private final Hole WOMEN_HOLE13 = new Hole(13, 73, 3);
    private final Hole WOMEN_HOLE14 = new Hole(14, 117, 3);
    private final Hole WOMEN_HOLE15 = new Hole(15, 277, 4);
    private final Hole WOMEN_HOLE16 = new Hole(16, 375, 4);
    private final Hole WOMEN_HOLE17 = new Hole(17, 112, 3);
    private final Hole WOMEN_HOLE18 = new Hole(19, 161, 3);

    public List<Hole> getWomenHoles() {
        List<Hole> holes = new ArrayList<>();
        holes.add(WOMEN_HOLE1);
        holes.add(WOMEN_HOLE2);
        holes.add(WOMEN_HOLE3);
        holes.add(WOMEN_HOLE4);
        holes.add(WOMEN_HOLE5);
        holes.add(WOMEN_HOLE6);
        holes.add(WOMEN_HOLE7);
        holes.add(WOMEN_HOLE8);
        holes.add(WOMEN_HOLE9);
        holes.add(WOMEN_HOLE10);
        holes.add(WOMEN_HOLE11);
        holes.add(WOMEN_HOLE12);
        holes.add(WOMEN_HOLE13);
        holes.add(WOMEN_HOLE14);
        holes.add(WOMEN_HOLE15);
        holes.add(WOMEN_HOLE16);
        holes.add(WOMEN_HOLE17);
        holes.add(WOMEN_HOLE18);
        return holes;
    }

    private final HoleLayout WOMEN_LAYOUT = new HoleLayout(3L, getWomenHoles(), COURSE,
            LayoutType.WOMEN, 1955, 1726, 64, 62.1, 104);

    public List<HoleLayout> getHoleLayouts() {
        return new ArrayList<>(Arrays
                .asList(CHAMPIONSHIP_LAYOUT, MENS_LAYOUT, WOMEN_LAYOUT));
    }

    @Test
    public void getAllCourses_returns_HTTP_OK() throws Exception {
        List<Course> courses = new ArrayList<>(Collections.singletonList(COURSE));

        Mockito.when(courseService.getAllCourses()).thenReturn(courses);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/courses")
                .content(this.objectMapper.writeValueAsString(courses))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void addNewCourse_returns_HTTP_CREATED() throws Exception {
        Course course = new Course();
        course.setCourseName("Vista Valencia");
        course.setHoleLayout(getHoleLayouts());

        Mockito.when(courseService.createCourse(Mockito.any(Course.class))).thenReturn(course);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/courses")
                .content(this.objectMapper.writeValueAsString(course.getHoleLayout()))
                .content(this.objectMapper.writeValueAsString(course))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.courseName", is("Vista Valencia")));
    }

    @Test
    public void getCourseById_returns_HTTP_OK() throws Exception {
        COURSE.setId(1L);

        Mockito.when(courseService.getCourseById(COURSE.getId())).thenReturn(COURSE);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/courses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(this.objectMapper.writeValueAsString(COURSE));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void deleteCourse_returns_HTTP_OK() throws Exception {
        Mockito.when(courseService.getCourseById(COURSE.getId())).thenReturn(COURSE);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
