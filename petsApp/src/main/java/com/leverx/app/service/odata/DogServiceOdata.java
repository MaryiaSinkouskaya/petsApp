package com.leverx.app.service.odata;

import com.leverx.app.edm.dog.DogEdm;
import com.leverx.app.edm.user.UserEdm;
import com.leverx.app.entity.dog.Dog;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DogServiceOdata extends OdataCommonService<DogEdm, UserEdm, Dog> {

    DogEdm find(long id);

    List<DogEdm> findAll();

    UserEdm readRelatedData(Object sourceData, String targetEntityName) throws EdmException;

    Dog save(Object data);

    void delete(long id);

    DogEdm getNewEdm();
}
