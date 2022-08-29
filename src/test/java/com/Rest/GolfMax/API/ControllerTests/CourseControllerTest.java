package com.Rest.GolfMax.API.ControllerTests;

import com.Rest.GolfMax.API.Controllers.CourseController;
import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Models.Hole;
import com.Rest.GolfMax.API.Models.HoleLayout;
import com.Rest.GolfMax.API.Models.LayoutType;
import com.Rest.GolfMax.API.Services.Interfaces.CourseService;
import com.Rest.GolfMax.API.Services.Interfaces.HoleLayoutService;
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
    @MockBean
    private HoleLayoutService holeLayoutService;

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

    private final HoleLayout CHAMPIONSHIP_LAYOUT = new HoleLayout(1, getChampionshipHoles(), COURSE,
            LayoutType.CHAMPIONSHIP, 2226, 1966, 61, 65.4, 113);

    private final Hole MENS_HOLE1 = new Hole(1, 336, 4);
    private final Hole MENS_HOLE2 = new Hole(2, 202, 3);
    private final Hole MENS_HOLE3 = new Hole(3, 179, 3);
    private final Hole MENS_HOLE4 = new Hole(4, 365, 4);
    private final Hole MENS_HOLE5 = new Hole(5, 85, 3);
    private final Hole MENS_HOLE6 = new Hole(6, 141, 3);
    private final Hole MENS_HOLE7 = new Hole(7, 305, 4);
    private final Hole MENS_HOLE8 = new Hole(8, 129, 3);
    private final Hole MENS_HOLE9 = new Hole(9, 360, 4);
    private final Hole MENS_HOLE10 = new Hole(10, 424, 4);
    private final Hole MENS_HOLE11 = new Hole(11, 166, 3);
    private final Hole MENS_HOLE12 = new Hole(12, 71, 3);
    private final Hole MENS_HOLE13 = new Hole(13, 74, 3);
    private final Hole MENS_HOLE14 = new Hole(14, 117, 3);
    private final Hole MENS_HOLE15 = new Hole(15, 311, 4);
    private final Hole MENS_HOLE16 = new Hole(16, 389, 4);
    private final Hole MENS_HOLE17 = new Hole(17, 145, 3);
    private final Hole MENS_HOLE18 = new Hole(19, 169, 3);

    public List<Hole> getMensHoles() {
        List<Hole> holes = new ArrayList<>();
        holes.add(MENS_HOLE1);
        holes.add(MENS_HOLE2);
        holes.add(MENS_HOLE3);
        holes.add(MENS_HOLE4);
        holes.add(MENS_HOLE5);
        holes.add(MENS_HOLE6);
        holes.add(MENS_HOLE7);
        holes.add(MENS_HOLE8);
        holes.add(MENS_HOLE9);
        holes.add(MENS_HOLE10);
        holes.add(MENS_HOLE11);
        holes.add(MENS_HOLE12);
        holes.add(MENS_HOLE13);
        holes.add(MENS_HOLE14);
        holes.add(MENS_HOLE15);
        holes.add(MENS_HOLE16);
        holes.add(MENS_HOLE17);
        holes.add(MENS_HOLE18);
        return holes;
    }

    private final HoleLayout MENS_LAYOUT = new HoleLayout(2, getMensHoles(), COURSE,
            LayoutType.MENS, 2102, 1866, 61, 63.6, 107);

    private final Hole WOMENS_HOLE1 = new Hole(1, 312, 4);
    private final Hole WOMENS_HOLE2 = new Hole(2, 184, 4);
    private final Hole WOMENS_HOLE3 = new Hole(3, 169, 3);
    private final Hole WOMENS_HOLE4 = new Hole(4, 356, 4);
    private final Hole WOMENS_HOLE5 = new Hole(5, 68, 3);
    private final Hole WOMENS_HOLE6 = new Hole(6, 124, 3);
    private final Hole WOMENS_HOLE7 = new Hole(7, 288, 4);
    private final Hole WOMENS_HOLE8 = new Hole(8, 114, 3);
    private final Hole WOMENS_HOLE9 = new Hole(9, 340, 5);
    private final Hole WOMENS_HOLE10 = new Hole(10, 393, 5);
    private final Hole WOMENS_HOLE11 = new Hole(11, 147, 3);
    private final Hole WOMENS_HOLE12 = new Hole(12, 71, 3);
    private final Hole WOMENS_HOLE13 = new Hole(13, 73, 3);
    private final Hole WOMENS_HOLE14 = new Hole(14, 117, 3);
    private final Hole WOMENS_HOLE15 = new Hole(15, 277, 4);
    private final Hole WOMENS_HOLE16 = new Hole(16, 375, 4);
    private final Hole WOMENS_HOLE17 = new Hole(17, 112, 3);
    private final Hole WOMENS_HOLE18 = new Hole(19, 161, 3);

    public List<Hole> getWomensHoles() {
        List<Hole> holes = new ArrayList<>();
        holes.add(WOMENS_HOLE1);
        holes.add(WOMENS_HOLE2);
        holes.add(WOMENS_HOLE3);
        holes.add(WOMENS_HOLE4);
        holes.add(WOMENS_HOLE5);
        holes.add(WOMENS_HOLE6);
        holes.add(WOMENS_HOLE7);
        holes.add(WOMENS_HOLE8);
        holes.add(WOMENS_HOLE9);
        holes.add(WOMENS_HOLE10);
        holes.add(WOMENS_HOLE11);
        holes.add(WOMENS_HOLE12);
        holes.add(WOMENS_HOLE13);
        holes.add(WOMENS_HOLE14);
        holes.add(WOMENS_HOLE15);
        holes.add(WOMENS_HOLE16);
        holes.add(WOMENS_HOLE17);
        holes.add(WOMENS_HOLE18);
        return holes;
    }

    private final HoleLayout WOMENS_LAYOUT = new HoleLayout(3, getWomensHoles(), COURSE,
            LayoutType.WOMENS, 1955, 1726, 64, 62.1, 104);

    public List<HoleLayout> getHoleLayouts() {
        return new ArrayList<>(Arrays
                .asList(CHAMPIONSHIP_LAYOUT, MENS_LAYOUT, WOMENS_LAYOUT));
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
        COURSE.setCourseName("Vista Valencia");

        Mockito.when(courseService.createCourse(Mockito.any(Course.class))).thenReturn(COURSE);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/courses")
                .content(this.objectMapper.writeValueAsString(COURSE))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.courseName", is("Vista Valencia")));
    }

    @Test
    public void getCourseById_returns_HTTP_OK() throws Exception {
        COURSE.setId(1);

        Mockito.when(courseService.getCourseById(COURSE.getId())).thenReturn(Optional.of(COURSE));

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
        Mockito.when(courseService.getCourseById(COURSE.getId())).thenReturn(Optional.of(COURSE));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
