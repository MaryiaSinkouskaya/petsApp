package com.leverx.app.repository;

import com.leverx.app.entity.cat.Cat;
import com.leverx.app.entity.cat.RequestCat;

import java.util.List;

public interface CatRepository {
    List<Cat> findAll();

    Cat create(RequestCat cat);

    void delete(long id);
}
