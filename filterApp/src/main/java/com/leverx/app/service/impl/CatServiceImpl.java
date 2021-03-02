package com.leverx.app.service.impl;

import com.leverx.app.entity.cat.Cat;
import com.leverx.app.entity.cat.RequestCat;
import com.leverx.app.repository.CatRepository;
import com.leverx.app.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    @Override
    public Cat create(RequestCat cat) {
        return catRepository.create(cat);
    }

    @Override
    public List<Cat> findAll() {
        return catRepository.findAll();
    }

    @Override
    public void delete(long id) {
        catRepository.delete(id);
    }
}
