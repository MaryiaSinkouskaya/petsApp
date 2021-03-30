package com.leverx.app.service.odata.impl;

import com.leverx.app.config.odata.edm.CatEdm;
import com.leverx.app.repository.CatRepository;
import com.leverx.app.service.odata.CatServiceOdata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertCat;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertCats;

@RequiredArgsConstructor
@Service
public class CatServiceOdataImpl implements CatServiceOdata {

    private final CatRepository catRepository;

    @Override
    public CatEdm find(long id) {
        return convertCat(catRepository.findById(id).get());
    }

    @Override
    public List<CatEdm> findAll() {
        return convertCats(catRepository.findAll());
    }

}
