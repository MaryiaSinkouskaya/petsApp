package com.leverx.app.service.odata.impl;

import com.leverx.app.edm.dog.DogEdm;
import com.leverx.app.edm.user.UserEdm;
import com.leverx.app.entity.dog.Dog;
import com.leverx.app.service.jpa.DogService;
import com.leverx.app.service.odata.DogServiceOdata;
import lombok.RequiredArgsConstructor;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.leverx.app.edm.mapper.EdmMapper.convertDog;
import static com.leverx.app.edm.mapper.EdmMapper.convertDogs;
import static com.leverx.app.edm.constants.EntityConstants.ENTITY_SET_NAME_USERS;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

@RequiredArgsConstructor
@Service
public class DogServiceOdataImpl implements DogServiceOdata {

    private final DogService dogService;

    @Override
    public DogEdm find(long id) {
        return convertDog(dogService.find(id).get());
    }

    @Override
    public List<DogEdm> findAll() {
        return convertDogs(dogService.findAll());
    }

    @Override
    public UserEdm readRelatedData(Object sourceData, String targetEntityName) throws EdmException {
        DogEdm dog = (DogEdm) sourceData;
        if (ENTITY_SET_NAME_USERS.equals(targetEntityName)) {
            return dog.getUser();
        }
        throw new EdmException(ENTITY);
    }

    @Override
    public Dog save(Object data) {
        DogEdm dogEdm = (DogEdm) data;
        Dog dog = convertDog(dogEdm);
        return dogService.create(dog);
    }

    @Override
    public void delete(long id) {
        dogService.delete(id);
    }

    @Override
    public DogEdm getNewEdm() {
        return new DogEdm();
    }
}
