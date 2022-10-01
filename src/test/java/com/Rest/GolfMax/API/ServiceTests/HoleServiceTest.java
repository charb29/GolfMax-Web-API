package com.Rest.GolfMax.API.ServiceTests;

import com.Rest.GolfMax.API.Models.Hole;
import com.Rest.GolfMax.API.Repositories.HoleRepository;
import com.Rest.GolfMax.API.Services.Implementations.HoleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HoleServiceTest {

    @Mock
    private HoleRepository holeRepository;
    @InjectMocks
    private HoleServiceImpl holeService;

    @Test
    public void createHole_should_return_hole() {
        Hole holeRequest = new Hole();
        holeRequest.setId(1L);

        when(holeService.createHole(holeRequest)).thenReturn(holeRequest);

        Hole holeResponse = holeService.createHole(holeRequest);

        assertThat(holeResponse.getId()).isSameAs(holeRequest.getId());
    }
}
