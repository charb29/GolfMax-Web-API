package com.Rest.GolfMax.API.Services.Implementations;

import com.Rest.GolfMax.API.Models.Hole;
import com.Rest.GolfMax.API.Repositories.HoleRepository;
import com.Rest.GolfMax.API.Services.Interfaces.HoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;


@Service
@Transactional
@RequestScope
public class HoleServiceImpl implements HoleService {

    private final HoleRepository HOLE_REPOSITORY;

    public HoleServiceImpl(HoleRepository holeRepository) {
        super();
        this.HOLE_REPOSITORY = holeRepository;
    }

    @Override
    public Hole createHole(Hole holes) {
        return HOLE_REPOSITORY.save(holes);
    }
}
