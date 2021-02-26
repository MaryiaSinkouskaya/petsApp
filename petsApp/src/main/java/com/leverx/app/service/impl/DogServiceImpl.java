package com.leverx.app.service.impl;

import com.leverx.app.entity.dog.Dog;
import com.leverx.app.repository.DogRepository;
import com.leverx.app.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

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

    @Override
    public List<Dog> findAll() {
        return dogRepository.findAll();
    }

    @Override
    public void delete(long id) {
        dogRepository.deleteById(id);
    }

    @Override
    public Dog update(Dog dog) {
        return dogRepository.save(dog);
    }
}
