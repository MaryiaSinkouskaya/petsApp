package com.leverx.app.service;

import com.leverx.app.entity.request.dog.RequestDog;
import com.leverx.app.entity.response.dog.ResponseDog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DogService {

    List<ResponseDog> findAll();

    ResponseDog create(RequestDog dog);

    void delete(long id);
}
