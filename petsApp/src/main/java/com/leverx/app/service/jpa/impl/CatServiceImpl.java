package com.leverx.app.service.jpa.impl;

import com.leverx.app.entity.cat.Cat;
import com.leverx.app.repository.CatRepository;
import com.leverx.app.service.jpa.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    @Override
    public Optional<Cat> find(long id) {
        return catRepository.findById(id);
    }

    @Override
    public List<Cat> findAll() {
        return catRepository.findAll();
    }

    @Override
    public Cat create(Cat cat) {
        return catRepository.save(cat);
    }

    @Override
    public Cat update(Cat cat) {
        return catRepository.save(cat);
    }

    @Override
    public void delete(long id) {
        catRepository.deleteById(id);
    }
}
