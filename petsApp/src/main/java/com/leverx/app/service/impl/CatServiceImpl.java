package com.leverx.app.service.impl;

import com.leverx.app.entity.Cat;
import com.leverx.app.repository.CatRepository;
import com.leverx.app.service.CatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;


    public CatServiceImpl(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Transactional
    @Override
    public Optional<Cat> find(long id) {
        return catRepository.findById(id);
    }

    @Transactional
    @Override
    public List<Cat> findAll() {
        return catRepository.findAll();
    }

    @Transactional
    @Override
    public Cat create(Cat cat) {
        return catRepository.save(cat);
    }

    @Transactional
    @Override
    public Cat update(Cat cat) {
        return catRepository.save(cat);
    }

    @Transactional
    @Override
    public void delete(Cat cat) {
         catRepository.delete(cat);
    }
}
