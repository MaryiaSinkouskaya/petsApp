package com.leverx.app.service.odata;

import com.leverx.app.edm.dog.DogEdm;
import com.leverx.app.edm.user.UserEdm;
import com.leverx.app.entity.dog.Dog;
import org.springframework.stereotype.Service;

@Service
public interface DogServiceOdata extends OdataCommonService<DogEdm, UserEdm, Dog> {
}
