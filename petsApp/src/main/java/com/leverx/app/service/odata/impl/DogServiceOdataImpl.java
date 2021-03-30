package com.leverx.app.service.odata.impl;

import com.leverx.app.config.odata.edm.DogEdm;
import com.leverx.app.repository.DogRepository;
import com.leverx.app.service.odata.DogServiceOdata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertDog;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertDogs;

@RequiredArgsConstructor
@Service
public class DogServiceOdataImpl implements DogServiceOdata {

    private final DogRepository dogRepository;

    @Override
    public DogEdm find(long id) {
        return convertDog(dogRepository.findById(id).get());
    }

    @Override
    public List<DogEdm> findAll() {
        return convertDogs(dogRepository.findAll());
    }
}
