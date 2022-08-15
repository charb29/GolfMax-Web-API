package com.Rest.GolfMax.API;

import com.Rest.GolfMax.API.Controllers.WomenTeesHoleYardagesController;
import com.Rest.GolfMax.API.Models.*;
import com.Rest.GolfMax.API.Repositories.WomenTeesHoleYardagesRepository;
import com.Rest.GolfMax.API.Services.WomenTeesHoleYardagesService;
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

@WebMvcTest(WomenTeesHoleYardagesController.class)
public class WomenTeesHoleYardagesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private WomenTeesHoleYardagesRepository repository;
    @MockBean
    private WomenTeesHoleYardagesService service;

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
    private WomenTeesHoleYardages YARDAGES_4 = new WomenTeesHoleYardages(1, TEES_1, 357,
            213, 190, 371, 103, 166, 316, 139,
            371, 439, 182, 71, 82, 121, 332,
            404, 156, 179 );
    private WomenTeesHoleYardages YARDAGES_5 = new WomenTeesHoleYardages(2, TEES_2, 357,
            213, 190, 371, 103, 166, 316, 139,
            371, 439, 182, 71, 82, 121, 332,
            404, 156, 179 );
    private WomenTeesHoleYardages YARDAGES_6 = new WomenTeesHoleYardages(3, TEES_3, 357,
            213, 190, 371, 103, 166, 316, 139,
            371, 439, 182, 71, 82, 121, 332,
            404, 156, 179 );

    @Test
    public void getAllHoleYardages() throws Exception {
        List<WomenTeesHoleYardages> yardagesList = new ArrayList<>(Arrays.asList
                (YARDAGES_4, YARDAGES_5, YARDAGES_6));

        Mockito.when(service.listAllHoleYardages()).thenReturn(yardagesList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/courses/women-tees/yardages/list-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void addNewTeeYardages() throws Exception {
        Mockito.when(repository.save(YARDAGES_1)).thenReturn(YARDAGES_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/courses/women-tees/yardages/add-new")
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
                        .get("/courses/women-tees/yardages/course/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void deleteTeeYardagesById() throws Exception {
        Mockito.when(repository.getById(YARDAGES_1.getId())).thenReturn(YARDAGES_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/courses/women-tees/yardages/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
