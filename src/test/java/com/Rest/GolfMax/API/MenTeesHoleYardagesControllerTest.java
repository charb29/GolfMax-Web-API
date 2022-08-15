package com.Rest.GolfMax.API;

import com.Rest.GolfMax.API.Controllers.MenTeesHoleYardagesController;
import com.Rest.GolfMax.API.Models.*;
import com.Rest.GolfMax.API.Repositories.MenTeesHoleYardagesRepository;
import com.Rest.GolfMax.API.Services.MenTeesHoleYardagesService;
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

@WebMvcTest(MenTeesHoleYardagesController.class)
public class MenTeesHoleYardagesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MenTeesHoleYardagesRepository repository;
    @MockBean
    private MenTeesHoleYardagesService service;

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
    private MenTeesHoleYardages YARDAGES_4 = new MenTeesHoleYardages(1, TEES_1, 357,
            213, 190, 371, 103, 166, 316, 139,
            371, 439, 182, 71, 82, 121, 332,
            404, 156, 179 );
    private MenTeesHoleYardages YARDAGES_5 = new MenTeesHoleYardages(2, TEES_2, 357,
            213, 190, 371, 103, 166, 316, 139,
            371, 439, 182, 71, 82, 121, 332,
            404, 156, 179 );
    private MenTeesHoleYardages YARDAGES_6 = new MenTeesHoleYardages(3, TEES_3, 357,
            213, 190, 371, 103, 166, 316, 139,
            371, 439, 182, 71, 82, 121, 332,
            404, 156, 179 );

    @Test
    public void getAllHoleYardages() throws Exception {
        List<MenTeesHoleYardages> yardagesList = new ArrayList<>(Arrays.asList
                (YARDAGES_4, YARDAGES_5, YARDAGES_6));

        Mockito.when(service.listAllHoleYardages()).thenReturn(yardagesList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/courses/men-tees/yardages/list-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void addNewTeeYardages() throws Exception {
        Mockito.when(repository.save(YARDAGES_1)).thenReturn(YARDAGES_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/courses/men-tees/yardages/add-new")
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
                        .get("/courses/men-tees/yardages/course/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void deleteTeeYardagesById() throws Exception {
        Mockito.when(repository.getById(YARDAGES_1.getId())).thenReturn(YARDAGES_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/courses/men-tees/yardages/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

