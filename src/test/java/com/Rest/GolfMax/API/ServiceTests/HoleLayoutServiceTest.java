package com.Rest.GolfMax.API.ServiceTests;

import com.Rest.GolfMax.API.Models.HoleLayout;
import com.Rest.GolfMax.API.Repositories.HoleLayoutRepository;
import com.Rest.GolfMax.API.Services.Implementations.HoleLayoutServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HoleLayoutServiceTest {

    @Mock
    private HoleLayoutRepository holeLayoutRepository;
    @InjectMocks
    private HoleLayoutServiceImpl holeLayoutService;

    @Test
    public void createHoleLayout_should_return_holeLayout() {
        HoleLayout holeLayoutRequest = new HoleLayout();
        holeLayoutRequest.setId(1L);

        when(holeLayoutService.createHoleLayout(List.of(holeLayoutRequest)))
                .thenReturn(List.of(holeLayoutRequest));

        List<HoleLayout> holeLayoutResponse = holeLayoutService.createHoleLayout(List.of(holeLayoutRequest));

        assertThat(holeLayoutResponse.get(0).getId()).isSameAs(holeLayoutRequest.getId());
    }
}
