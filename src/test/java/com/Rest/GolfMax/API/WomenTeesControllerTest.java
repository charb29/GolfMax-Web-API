package com.Rest.GolfMax.API;

import com.Rest.GolfMax.API.Controllers.ChampionshipTeesController;
import com.Rest.GolfMax.API.Controllers.WomenTeesController;
import com.Rest.GolfMax.API.Models.*;
import com.Rest.GolfMax.API.Repositories.ChampionshipTeesRepository;
import com.Rest.GolfMax.API.Repositories.WomenTeesRepository;
import com.Rest.GolfMax.API.Services.ChampionshipTeesService;
import com.Rest.GolfMax.API.Services.WomenTeesService;
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

@WebMvcTest(WomenTeesController.class)
public class WomenTeesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private WomenTeesRepository repository;
    @MockBean
    private WomenTeesService service;

    private Course COURSE_1 = new Course(1, "Vista Valencia Golf Course", 61);
    private Course COURSE_2 = new Course(2, "Brookside Golf Club (E O Nay)", 70);
    private Course COURSE_3 = new Course(3, "Scholl Canyon Golf", 60);
    private WomenTeesHoleYardages YARDAGES_1 = new WomenTeesHoleYardages();
    private WomenTeesHoleYardages YARDAGES_2 = new WomenTeesHoleYardages();
    private WomenTeesHoleYardages YARDAGES_3 = new WomenTeesHoleYardages();
    private WomenTees TEES_1 = new WomenTees(1, YARDAGES_1, COURSE_1, 65.4, 113,
            4192, 4, 3, 3, 4, 3, 3, 4, 3, 4, 4,
            3, 3, 3, 3, 4, 4, 3, 3);
    private WomenTees TEES_2 = new WomenTees(2, YARDAGES_2, COURSE_2, 69, 121,
            5837, 4, 4, 4, 4, 3, 4, 4, 4, 4, 3,
            5, 3, 4, 4, 3, 5, 4, 4);
    private WomenTees TEES_3 = new WomenTees(3, YARDAGES_3, COURSE_3, 54.8, 86,
            2839, 4, 3, 4, 3, 3, 3, 3, 3, 4, 4,
            3, 3, 4, 3, 4, 3, 3, 3);


    @Test
    public void getAllChampionshipTees() throws Exception {
        List<WomenTees> teesList = new ArrayList<>(Arrays.asList(TEES_1, TEES_2, TEES_3));

        Mockito.when(service.listAllTees()).thenReturn(teesList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/courses/women-tees/list-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].totalYards", is(2839)));
    }

    @Test
    public void addNewTeePars() throws Exception {
        Mockito.when(repository.save(TEES_1)).thenReturn(TEES_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/courses/women-tees/add-new")
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
                        .get("/courses/women-tees/course/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void deleteTeesById() throws Exception {
        Mockito.when(repository.getById(TEES_1.getId())).thenReturn(TEES_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/courses/women-tees/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
