package com.leverx.app.repository;

import com.leverx.app.entity.request.dog.RequestDog;
import com.leverx.app.entity.response.dog.ResponseDog;

import java.util.List;

public interface DogRepository {

    List<ResponseDog> findAll();

    ResponseDog create(RequestDog dog);

    void delete(long id);

}
