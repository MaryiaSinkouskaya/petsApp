package com.leverx.app.service.odata.impl;

import com.leverx.app.config.odata.edm.CatEdm;
import com.leverx.app.config.odata.edm.UserEdm;
import com.leverx.app.repository.CatRepository;
import com.leverx.app.service.odata.CatServiceOdata;
import lombok.RequiredArgsConstructor;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertCat;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertCats;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_USERS;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

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

    @Override
    public UserEdm readRelatedData(Object sourceData, String targetEntityName) throws EdmException {
        CatEdm cat = (CatEdm) sourceData;
        if (ENTITY_SET_NAME_USERS.equals(targetEntityName)) {
            return cat.getUser();
        }
        throw new EdmException(ENTITY);
    }
}
