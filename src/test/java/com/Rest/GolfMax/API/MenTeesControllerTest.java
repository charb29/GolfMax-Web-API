package com.Rest.GolfMax.API;

import com.Rest.GolfMax.API.Controllers.MenTeesController;
import com.Rest.GolfMax.API.Models.ChampionshipTees;
import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Models.MenTees;
import com.Rest.GolfMax.API.Models.MenTeesHoleYardages;
import com.Rest.GolfMax.API.Repositories.MenTeesRepository;
import com.Rest.GolfMax.API.Services.MenTeesService;
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

@WebMvcTest(MenTeesController.class)
public class MenTeesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MenTeesRepository repository;
    @MockBean
    private MenTeesService service;

    private Course COURSE_1 = new Course(1, "Vista Valencia Golf Course", 61);
    private Course COURSE_2 = new Course(2, "Brookside Golf Club (E O Nay)", 70);
    private Course COURSE_3 = new Course(3, "Scholl Canyon Golf", 60);
    private MenTeesHoleYardages YARDAGES_1 = new MenTeesHoleYardages();
    private MenTeesHoleYardages YARDAGES_2 = new MenTeesHoleYardages();
    private MenTeesHoleYardages YARDAGES_3 = new MenTeesHoleYardages();
    private MenTees TEES_1 = new MenTees(1, YARDAGES_1, COURSE_1, 65.4, 113,
            4192, 4, 3, 3, 4, 3, 3, 4, 3, 4, 4,
            3, 3, 3, 3, 4, 4, 3, 3);
    private MenTees TEES_2 = new MenTees(2, YARDAGES_2, COURSE_2, 69, 121,
            5837, 4, 4, 4, 4, 3, 4, 4, 4, 4, 3,
            5, 3, 4, 4, 3, 5, 4, 4);
    private MenTees TEES_3 = new MenTees(3, YARDAGES_3, COURSE_3, 54.8, 86,
            2839, 4, 3, 4, 3, 3, 3, 3, 3, 4, 4,
            3, 3, 4, 3, 4, 3, 3, 3);

    @Test
    public void getAllMenTees() throws Exception {
        List<MenTees> teesList = new ArrayList<>(Arrays.asList(TEES_1, TEES_2, TEES_3));

        Mockito.when(service.listAllTees()).thenReturn(teesList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/courses/men-tees/list-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].totalYards", is(2839)));
    }

    @Test
    public void addNewTeePars() throws Exception {
        Mockito.when(repository.save(TEES_1)).thenReturn(TEES_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/courses/men-tees/add-new")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(TEES_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.courseRating", is(65.4)));
    }

    @Test
    public void getTeesByCourseId() throws Exception {
        Mockito.when(service
                        .getTeesByCourseId(COURSE_1.getId()))
                .thenReturn(TEES_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/courses/men-tees/course/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void deleteTeesById() throws Exception {
        Mockito.when(repository.getById(TEES_1.getId())).thenReturn(TEES_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/courses/men-tees/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
