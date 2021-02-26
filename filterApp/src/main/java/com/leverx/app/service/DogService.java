package com.leverx.app.service;

import com.leverx.app.entity.dog.Dog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DogService {

    List<Dog> findAll();

    Dog create(Dog dog);
}
