package com.leverx.app.service.odata.impl;

import com.leverx.app.config.odata.edm.DogEdm;
import com.leverx.app.config.odata.edm.UserEdm;
import com.leverx.app.repository.DogRepository;
import com.leverx.app.service.odata.DogServiceOdata;
import lombok.RequiredArgsConstructor;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertDog;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertDogs;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_USERS;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

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

    @Override
    public UserEdm readRelatedData(Object sourceData, String targetEntityName) throws EdmException {
        DogEdm dog = (DogEdm) sourceData;
        if (ENTITY_SET_NAME_USERS.equals(targetEntityName)) {
            return dog.getUser();
        }
        throw new EdmException(ENTITY);
    }
}
