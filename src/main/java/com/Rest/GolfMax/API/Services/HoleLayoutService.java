package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.HoleLayout;
import com.Rest.GolfMax.API.Repositories.HoleLayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Service
@Transactional
@RequestScope
public class HoleLayoutService {
    @Autowired
    private HoleLayoutRepository repository;

    public void saveHoleLayout(List<HoleLayout> holeLayout) {
        repository.saveAll(holeLayout);
    }
}
