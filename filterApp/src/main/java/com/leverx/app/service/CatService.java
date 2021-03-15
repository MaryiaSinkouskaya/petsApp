package com.leverx.app.service;

import com.leverx.app.entity.request.cat.RequestCat;
import com.leverx.app.entity.response.cat.ResponseCat;

import java.util.List;

public interface CatService {

    List<ResponseCat> findAll();

    ResponseCat create(RequestCat cat);

    void delete(long id);
}
