package com.leverx.app.service.odata;

import com.leverx.app.edm.pet.PetEdm;
import com.leverx.app.edm.user.UserEdm;
import com.leverx.app.entity.pet.Pet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetServiceOdata extends OdataCommonService<PetEdm, UserEdm, Pet> {

    PetEdm find(long id);

    List<PetEdm> findAll();

    UserEdm readRelatedData(Object sourceData, String targetEntityName) throws EdmException;

    Pet save(Object data) throws ODataNotImplementedException;

    void delete(long id) throws ODataNotImplementedException;

    PetEdm getNewEdm() throws ODataNotImplementedException;
}


