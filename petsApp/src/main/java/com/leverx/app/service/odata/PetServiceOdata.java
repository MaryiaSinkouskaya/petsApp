package com.leverx.app.service.odata;

import com.leverx.app.config.odata.edm.PetEdm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetServiceOdata extends OdataCommonService<PetEdm> {

    PetEdm find(long id);

    List<PetEdm> findAll();
}
