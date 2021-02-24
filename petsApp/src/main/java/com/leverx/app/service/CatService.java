package com.leverx.app.service;

import com.leverx.app.entity.cat.Cat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CatService {

    Optional<Cat> find(long id);

    List<Cat> findAll();

    Cat create(Cat cat);

    void delete(long id);

    Cat update(Cat cat);
}
