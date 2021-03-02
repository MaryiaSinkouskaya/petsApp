package com.leverx.app.service;

import com.leverx.app.entity.DTO.ResponseDTO;
import com.leverx.app.entity.DTO.ResponseListDTO;
import com.leverx.app.entity.cat.Cat;
import com.leverx.app.entity.cat.RequestCat;
import com.leverx.app.entity.dog.Dog;
import com.leverx.app.entity.dog.RequestDog;
import com.leverx.app.entity.user.RequestUser;
import com.leverx.app.entity.user.User;
import org.springframework.stereotype.Service;

@Service
public interface CommonService {
    ResponseListDTO findAll();

    ResponseDTO createAll(RequestUser user, RequestCat cat, RequestDog dog);
}
