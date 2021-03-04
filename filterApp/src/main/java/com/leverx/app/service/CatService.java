package com.leverx.app.service;

import com.leverx.app.entity.request.cat.RequestCat;
import com.leverx.app.entity.response.cat.ResponseCat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatService {

    List<ResponseCat> findAll();

    ResponseCat create(RequestCat cat);

    void delete(long id);
}
