package com.leverx.app.service;

import com.leverx.app.entity.Dog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DogService {
    Optional<Dog> find(long id);
    List<Dog> findAll();
    Dog create(Dog dog);
    void delete(Dog dog);
    Dog update(Dog dog);
}
