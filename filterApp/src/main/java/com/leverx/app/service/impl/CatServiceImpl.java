package com.leverx.app.service.impl;

import com.leverx.app.entity.cat.Cat;
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
    public List<Cat> findAll() {
        return catRepository.findAll();
    }
}
