package com.leverx.app.service.odata;

import com.leverx.app.edm.cat.CatEdm;
import com.leverx.app.edm.user.UserEdm;
import com.leverx.app.entity.cat.Cat;
import org.springframework.stereotype.Service;

@Service
public interface CatServiceOdata extends OdataCommonService<CatEdm, UserEdm, Cat> {
}
