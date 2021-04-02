package com.leverx.app.service.odata;

import com.leverx.app.edm.cat.CatEdm;
import com.leverx.app.edm.user.UserEdm;
import com.leverx.app.entity.cat.Cat;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatServiceOdata extends OdataCommonService<CatEdm, UserEdm, Cat> {

    CatEdm find(long id);

    List<CatEdm> findAll();

    UserEdm readRelatedData(Object sourceData, String targetEntityName) throws EdmException;

    Cat save(Object data);

    void delete(long id);

    CatEdm getNewEdm();
}
