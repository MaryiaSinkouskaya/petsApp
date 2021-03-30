package com.leverx.app.service.odata;

import com.leverx.app.config.odata.edm.DogEdm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DogServiceOdata extends OdataCommonService<DogEdm> {

    DogEdm find(long id);

    List<DogEdm> findAll();
}
