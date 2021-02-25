package com.leverx.app.service.impl;

import com.leverx.app.entity.dog.Dog;
import com.leverx.app.repository.DogRepository;
import com.leverx.app.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

    @Override
    public List<Dog> findAll() {
        return dogRepository.findAll();
    }


}
