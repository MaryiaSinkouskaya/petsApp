package com.leverx.app.service.odata.impl;

import com.leverx.app.edm.pet.PetEdm;
import com.leverx.app.edm.user.UserEdm;
import com.leverx.app.entity.pet.Pet;
import com.leverx.app.service.jpa.PetService;
import com.leverx.app.service.odata.PetServiceOdata;
import lombok.RequiredArgsConstructor;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.leverx.app.edm.constants.EntityConstants.ENTITY_SET_NAME_USERS;
import static com.leverx.app.edm.mapper.EdmMapper.convertPet;
import static com.leverx.app.edm.mapper.EdmMapper.convertPets;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

@RequiredArgsConstructor
@Service
public class PetServiceOdataImpl implements PetServiceOdata {

    private final PetService petService;

    @Override
    public PetEdm find(long id) {
        return convertPet(petService.find(id).get());
    }

    @Override
    public List<PetEdm> findAll() {
        return convertPets(petService.findAll());
    }

    @Override
    public UserEdm readRelatedData(Object sourceData, String targetEntityName) throws EdmException {
        PetEdm pet = (PetEdm) sourceData;
        if (ENTITY_SET_NAME_USERS.equals(targetEntityName)) {
            return pet.getUser();
        }
        throw new EdmException(ENTITY);
    }

    @Override
    public Pet save(PetEdm data) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public void delete(long id) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public PetEdm getNewEdm() throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }
}
