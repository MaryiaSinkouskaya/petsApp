package com.leverx.app.service.impl;

import com.leverx.app.entity.DTO.ResponseDTO;
import com.leverx.app.entity.DTO.ResponseListDTO;
import com.leverx.app.entity.cat.RequestCat;
import com.leverx.app.entity.dog.RequestDog;
import com.leverx.app.entity.user.RequestUser;
import com.leverx.app.service.CatService;
import com.leverx.app.service.CommonService;
import com.leverx.app.service.DogService;
import com.leverx.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

    private final UserService userService;
    private final CatService catService;
    private final DogService dogService;


    @Override
    public ResponseListDTO findAll() {
        return new ResponseListDTO(
                dogService.findAll(),
                catService.findAll(),
                userService.findAll());
    }

    @Override
    public ResponseDTO createAll(RequestUser user, RequestCat cat, RequestDog dog) {
        return null;
    }
}
