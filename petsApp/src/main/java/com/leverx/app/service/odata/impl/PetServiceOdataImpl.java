package com.leverx.app.service.odata.impl;

import com.leverx.app.config.odata.edm.PetEdm;
import com.leverx.app.repository.PetRepository;
import com.leverx.app.service.odata.PetServiceOdata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertPet;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertPets;

@RequiredArgsConstructor
@Service
public class PetServiceOdataImpl implements PetServiceOdata {

    private final PetRepository petRepository;

    @Override
    public PetEdm find(long id) {
        return convertPet(petRepository.findById(id).get());
    }

    @Override
    public List<PetEdm> findAll() {
        return convertPets(petRepository.findAll());
    }
}
