package com.leverx.app.service;

import com.leverx.app.entity.cat.Cat;
import com.leverx.app.entity.cat.RequestCat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatService {


    List<Cat> findAll();

    Cat create(RequestCat cat);

    void delete(long id);
}
