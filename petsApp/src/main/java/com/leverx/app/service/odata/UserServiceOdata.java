package com.leverx.app.service.odata;

import com.leverx.app.config.odata.edm.UserEdm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceOdata extends OdataCommonService<UserEdm> {

    UserEdm find(long id);

    List<UserEdm> findAll();
}
