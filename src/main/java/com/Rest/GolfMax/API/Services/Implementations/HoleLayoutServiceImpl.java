package com.Rest.GolfMax.API.Services.Implementations;

import com.Rest.GolfMax.API.Models.HoleLayout;
import com.Rest.GolfMax.API.Repositories.HoleLayoutRepository;
import com.Rest.GolfMax.API.Services.Interfaces.HoleLayoutService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.transaction.Transactional;

@Service
@Transactional
@RequestScope
public class HoleLayoutServiceImpl implements HoleLayoutService {

    private final HoleLayoutRepository HOLE_LAYOUT_REPOSITORY;

    public HoleLayoutServiceImpl(HoleLayoutRepository holeLayoutRepository) {
        super();
        this.HOLE_LAYOUT_REPOSITORY = holeLayoutRepository;
    }

    @Override
    public HoleLayout createHoleLayout(HoleLayout holeLayout) {
        return HOLE_LAYOUT_REPOSITORY.save(holeLayout);
    }
}