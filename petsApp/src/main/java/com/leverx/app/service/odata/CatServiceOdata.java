package com.leverx.app.service.odata;

import com.leverx.app.config.odata.edm.CatEdm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatServiceOdata extends OdataCommonService<CatEdm> {

    CatEdm find(long id);

    List<CatEdm> findAll();
}
