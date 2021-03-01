package com.leverx.app.repository;

import com.leverx.app.entity.dog.Dog;

import java.util.List;

public interface DogRepository {
    List<Dog> findAll();

    Dog create(Dog dog);

    void delete(long id);

}
