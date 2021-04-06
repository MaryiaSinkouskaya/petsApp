package com.leverx.app.service.odata;

import com.leverx.app.edm.pet.PetEdm;
import com.leverx.app.edm.user.UserEdm;
import com.leverx.app.entity.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceOdata extends OdataCommonService<UserEdm, List<PetEdm>, User> {
}