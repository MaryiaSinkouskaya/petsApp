package com.leverx.app.service.impl;

import com.leverx.app.entity.DTO.ResponseDTO;
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
    public ResponseDTO findAll() {
        return new ResponseDTO(
                catService.findAll(),
                dogService.findAll(),
                userService.findAll());
    }
}
