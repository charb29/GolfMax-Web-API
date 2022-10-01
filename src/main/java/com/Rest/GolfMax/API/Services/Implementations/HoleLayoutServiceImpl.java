package com.Rest.GolfMax.API.Services.Implementations;

import com.Rest.GolfMax.API.Models.HoleLayout;
import com.Rest.GolfMax.API.Repositories.HoleLayoutRepository;
import com.Rest.GolfMax.API.Services.Interfaces.HoleLayoutService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequestScope
public class HoleLayoutServiceImpl implements HoleLayoutService {

    private final HoleLayoutRepository holeLayoutRepository;

    public HoleLayoutServiceImpl(HoleLayoutRepository holeLayoutRepository) {
        super();
        this.holeLayoutRepository = holeLayoutRepository;
    }

    @Override
    public List<HoleLayout> createHoleLayout(List<HoleLayout> holeLayout) {
        return holeLayoutRepository.saveAll(holeLayout);
    }
}
