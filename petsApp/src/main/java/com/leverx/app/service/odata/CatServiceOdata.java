package com.leverx.app.service.odata;

import com.leverx.app.config.odata.edm.CatEdm;
import com.leverx.app.config.odata.edm.UserEdm;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatServiceOdata extends OdataCommonService<CatEdm, UserEdm> {

    CatEdm find(long id);

    List<CatEdm> findAll();

    UserEdm readRelatedData(Object sourceData, String targetEntityName)throws EdmException;
}
