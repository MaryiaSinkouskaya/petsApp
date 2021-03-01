package com.leverx.app.service;

import com.leverx.app.entity.cat.Cat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatService {


    List<Cat> findAll();

    Cat create(Cat cat);

    void delete(long id);
}
