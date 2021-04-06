package com.leverx.app.service.odata.impl;

import com.leverx.app.edm.cat.CatEdm;
import com.leverx.app.edm.user.UserEdm;
import com.leverx.app.entity.cat.Cat;
import com.leverx.app.service.jpa.CatService;
import com.leverx.app.service.odata.CatServiceOdata;
import lombok.RequiredArgsConstructor;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.leverx.app.edm.constants.EntityConstants.ENTITY_SET_NAME_USERS;
import static com.leverx.app.edm.mapper.EdmMapper.convertCat;
import static com.leverx.app.edm.mapper.EdmMapper.convertCats;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

@RequiredArgsConstructor
@Service
public class CatServiceOdataImpl implements CatServiceOdata {

    private final CatService catService;

    @Override
    public CatEdm find(long id) {
        return convertCat(catService.find(id).get());
    }

    @Override
    public List<CatEdm> findAll() {
        return convertCats(catService.findAll());
    }

    @Override
    public UserEdm readRelatedData(Object sourceData, String targetEntityName) throws EdmException {
        CatEdm cat = (CatEdm) sourceData;
        if (ENTITY_SET_NAME_USERS.equals(targetEntityName)) {
            return cat.getUser();
        }
        throw new EdmException(ENTITY);
    }

    @Override
    public Cat save(CatEdm catEdm) {
        Cat cat = convertCat(catEdm);
        return catService.create(cat);
    }

    @Override
    public void delete(long id) {
        catService.delete(id);
    }

    @Override
    public CatEdm getNewEdm() {
        return new CatEdm();
    }
}
