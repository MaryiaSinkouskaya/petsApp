package com.leverx.app.service.odata;

import com.leverx.app.config.odata.edm.DogEdm;
import com.leverx.app.config.odata.edm.UserEdm;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DogServiceOdata extends OdataCommonService<DogEdm, UserEdm> {

    DogEdm find(long id);

    List<DogEdm> findAll();

    UserEdm readRelatedData(Object sourceData, String targetEntityName) throws EdmException;
}
