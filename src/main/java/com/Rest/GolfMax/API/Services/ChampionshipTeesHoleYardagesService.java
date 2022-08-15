package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.ChampionshipTeesHoleYardages;
import com.Rest.GolfMax.API.Repositories.ChampionshipTeesHoleYardagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequestScope
public class ChampionshipTeesHoleYardagesService {
    @Autowired
    private ChampionshipTeesHoleYardagesRepository repository;

    public List<ChampionshipTeesHoleYardages> listAllHoleYardages() {
        return repository.findAll();
    }

    public void addNewHoleYardages(ChampionshipTeesHoleYardages holeYardages) {
        repository.save(holeYardages);
    }

    public ChampionshipTeesHoleYardages getYardagesByCourseId(long courseId) {
        return repository.findByCourseId(courseId);
    }

    public void deleteYardagesById(Long id) {
        repository.deleteById(id);
    }
}
