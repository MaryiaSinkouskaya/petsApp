package com.leverx.app.service;

import com.leverx.app.entity.DTO.ResponseDTO;
import com.leverx.app.entity.DTO.ResponseListDTO;
import com.leverx.app.entity.cat.Cat;
import com.leverx.app.entity.dog.Dog;
import com.leverx.app.entity.user.User;
import org.springframework.stereotype.Service;

@Service
public interface CommonService {
    ResponseListDTO findAll();

    ResponseDTO createAll(User user, Cat cat, Dog dog);
}
