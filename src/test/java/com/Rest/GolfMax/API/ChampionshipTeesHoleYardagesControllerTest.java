package com.Rest.GolfMax.API;

import com.Rest.GolfMax.API.Controllers.ChampionshipTeesHoleYardagesController;
import com.Rest.GolfMax.API.Models.ChampionshipTees;
import com.Rest.GolfMax.API.Models.ChampionshipTeesHoleYardages;
import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Repositories.ChampionshipTeesHoleYardagesRepository;
import com.Rest.GolfMax.API.Services.ChampionshipTeesHoleYardagesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

@WebMvcTest(ChampionshipTeesHoleYardagesController.class)
public class ChampionshipTeesHoleYardagesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ChampionshipTeesHoleYardagesRepository repository;
    @MockBean
    private ChampionshipTeesHoleYardagesService service;

    private Course COURSE_1 = new Course(1, "Vista Valencia Golf Course", 61);
    private Course COURSE_2 = new Course(2, "Brookside Golf Club (E O Nay)", 70);
    private Course COURSE_3 = new Course(3, "Scholl Canyon Golf", 60);
    private ChampionshipTeesHoleYardages YARDAGES_1 = new ChampionshipTeesHoleYardages();
    private ChampionshipTeesHoleYardages YARDAGES_2 = new ChampionshipTeesHoleYardages();
    private ChampionshipTeesHoleYardages YARDAGES_3 = new ChampionshipTeesHoleYardages();
    private ChampionshipTees TEES_1 = new ChampionshipTees(1, YARDAGES_1, COURSE_1, 65.4, 113,
            4192, 4, 3, 3, 4, 3, 3, 4, 3, 4, 4,
            3, 3, 3, 3, 4, 4, 3, 3);
    private ChampionshipTees TEES_2 = new ChampionshipTees(2, YARDAGES_2, COURSE_2, 69, 121,
            5837, 4, 4, 4, 4, 3, 4, 4, 4, 4, 3,
            5, 3, 4, 4, 3, 5, 4, 4);
    private ChampionshipTees TEES_3 = new ChampionshipTees(3, YARDAGES_3, COURSE_3, 54.8, 86,
            2839, 4, 3, 4, 3, 3, 3, 3, 3, 4, 4,
            3, 3, 4, 3, 4, 3, 3, 3);
    private ChampionshipTeesHoleYardages YARDAGES_4 = new ChampionshipTeesHoleYardages(1, TEES_1, 357,
            213, 190, 371, 103, 166, 316, 139,
            371, 439, 182, 71, 82, 121, 332,
            404, 156, 179 );
    private ChampionshipTeesHoleYardages YARDAGES_5 = new ChampionshipTeesHoleYardages(2, TEES_2, 357,
            213, 190, 371, 103, 166, 316, 139,
            371, 439, 182, 71, 82, 121, 332,
            404, 156, 179 );
    private ChampionshipTeesHoleYardages YARDAGES_6 = new ChampionshipTeesHoleYardages(3, TEES_3, 357,
            213, 190, 371, 103, 166, 316, 139,
            371, 439, 182, 71, 82, 121, 332,
            404, 156, 179 );

    @Test
    public void getAllHoleYardages() throws Exception {
        List<ChampionshipTeesHoleYardages> yardagesList = new ArrayList<>(Arrays.asList
                (YARDAGES_4, YARDAGES_5, YARDAGES_6));

        Mockito.when(service.listAllHoleYardages()).thenReturn(yardagesList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/courses/championship-tees/yardages/list-all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].championshipTees.course.overallPar", is(61)));
    }

    @Test
    public void addNewTeeYardages() throws Exception {
        Mockito.when(repository.save(YARDAGES_1)).thenReturn(YARDAGES_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/courses/championship-tees/yardages/add-new")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(YARDAGES_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void getYardagesByCourseId() throws Exception {
        Mockito.when(service
                .getYardagesByCourseId(TEES_1.getCourse().getId()))
                .thenReturn(YARDAGES_1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/courses/championship-tees/yardages/course/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void deleteTeeYardagesById() throws Exception {
        Mockito.when(repository.getById(YARDAGES_1.getId())).thenReturn(YARDAGES_1);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/courses/championship-tees/yardages/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
