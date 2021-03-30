package com.leverx.app.service.odata;

import com.leverx.app.config.odata.edm.PetEdm;
import com.leverx.app.config.odata.edm.UserEdm;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceOdata extends OdataCommonService<UserEdm, List<PetEdm>> {

    UserEdm find(long id);

    List<UserEdm> findAll();

    List<PetEdm> readRelatedData(Object sourceData, String targetEntityName)throws EdmException;
}