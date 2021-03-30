package com.leverx.app.service.odata;

import com.leverx.app.config.odata.edm.PetEdm;
import com.leverx.app.config.odata.edm.UserEdm;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetServiceOdata extends OdataCommonService<PetEdm, UserEdm> {

    PetEdm find(long id);

    List<PetEdm> findAll();

    UserEdm readRelatedData(Object sourceData, String targetEntityName)throws EdmException;
}
