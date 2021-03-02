package com.leverx.app.service;

import com.leverx.app.entity.dog.Dog;
import com.leverx.app.entity.dog.RequestDog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DogService {

    List<Dog> findAll();

    Dog create(RequestDog dog);

    void delete(long id);

}
