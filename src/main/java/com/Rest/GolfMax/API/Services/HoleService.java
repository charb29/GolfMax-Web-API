package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.Hole;
import com.Rest.GolfMax.API.Repositories.HoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Service
@Transactional
@RequestScope
public class HoleService {
    @Autowired
    private HoleRepository repository;

    public void saveHoles(List<Hole> holes) {
        repository.saveAll(holes);
    }
}
