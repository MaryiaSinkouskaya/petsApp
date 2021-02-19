package com.leverx.app.service.impl;

import com.leverx.app.entity.Dog;
import com.leverx.app.repository.DogRepository;
import com.leverx.app.service.DogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {
   private final DogRepository dogRepository;


    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Transactional
    @Override
    public Optional<Dog> find(long id) {
        return dogRepository.findById(id);
    }

    @Transactional
    @Override
    public Dog create(Dog dog) {
        return dogRepository.save(dog);
    }

    @Transactional
    @Override
    public List<Dog> findAll() {
        return dogRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Dog dog) {
        dogRepository.delete(dog);
    }

    @Transactional
    @Override
    public Dog update(Dog dog) {
        return dogRepository.save(dog);
    }
}
