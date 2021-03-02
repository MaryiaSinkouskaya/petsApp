package com.leverx.app.repository;

import com.leverx.app.entity.dog.Dog;
import com.leverx.app.entity.dog.RequestDog;

import java.util.List;

public interface DogRepository {
    List<Dog> findAll();

    Dog create(RequestDog dog);

    void delete(long id);

}
