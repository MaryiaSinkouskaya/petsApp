package com.leverx.app.service.impl;

import com.leverx.app.service.CatService;
import com.leverx.app.service.CommonService;
import com.leverx.app.service.DogService;
import com.leverx.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

    private final UserService userService;
    private final CatService catService;
    private final DogService dogService;


    @Override
    public List findAll(String auth) {
        return asList(
                catService.findAll(auth).getBody(),
                dogService.findAll(auth).getBody(),
                userService.findAll(auth).getBody()
        );
    }
}
