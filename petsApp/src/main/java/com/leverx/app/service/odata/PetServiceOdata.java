package com.leverx.app.service.odata;

import com.leverx.app.edm.pet.PetEdm;
import com.leverx.app.edm.user.UserEdm;
import com.leverx.app.entity.pet.Pet;
import org.springframework.stereotype.Service;

@Service
public interface PetServiceOdata extends OdataCommonService<PetEdm, UserEdm, Pet> {
}


