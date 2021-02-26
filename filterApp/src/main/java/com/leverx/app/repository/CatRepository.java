package com.leverx.app.repository;

import com.leverx.app.entity.cat.Cat;

import java.util.List;

public interface CatRepository {
    List<Cat> findAll();
    Cat create(Cat cat);
}
