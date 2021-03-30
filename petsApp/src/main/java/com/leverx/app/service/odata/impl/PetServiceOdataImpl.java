package com.leverx.app.service.odata.impl;

import com.leverx.app.config.odata.edm.PetEdm;
import com.leverx.app.config.odata.edm.UserEdm;
import com.leverx.app.repository.PetRepository;
import com.leverx.app.service.odata.PetServiceOdata;
import lombok.RequiredArgsConstructor;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertPet;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertPets;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_USERS;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

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

    @Override
    public UserEdm readRelatedData(Object sourceData, String targetEntityName) throws EdmException {
        PetEdm pet = (PetEdm) sourceData;
        if (ENTITY_SET_NAME_USERS.equals(targetEntityName)) {
            return pet.getUser();
        }
        throw new EdmException(ENTITY);
    }
}
