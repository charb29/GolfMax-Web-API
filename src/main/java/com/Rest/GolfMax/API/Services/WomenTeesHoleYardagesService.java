package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.WomenTeesHoleYardages;
import com.Rest.GolfMax.API.Repositories.WomenTeesHoleYardagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequestScope
public class WomenTeesHoleYardagesService {
    @Autowired
    private WomenTeesHoleYardagesRepository repository;

    public List<WomenTeesHoleYardages> listAllHoleYardages() {
        return repository.findAll();
    }

    public void addNewHoleYardages(WomenTeesHoleYardages holeYardages) {
        repository.save(holeYardages);
    }

    public WomenTeesHoleYardages getYardagesByCourseId(long courseId) {
        return repository.findByCourseId(courseId);
    }

    public void deleteYardagesById(Long id) {
        repository.deleteById(id);
    }
}
