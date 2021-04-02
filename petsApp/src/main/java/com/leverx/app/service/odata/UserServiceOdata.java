package com.leverx.app.service.odata;

import com.leverx.app.edm.pet.PetEdm;
import com.leverx.app.edm.user.UserEdm;
import com.leverx.app.entity.user.User;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceOdata extends OdataCommonService<UserEdm, List<PetEdm>, User> {

    UserEdm find(long id);

    List<UserEdm> findAll();

    List<PetEdm> readRelatedData(Object sourceData, String targetEntityName) throws EdmException;

    User save(Object data);

    void delete(long id);

    UserEdm getNewEdm();
}