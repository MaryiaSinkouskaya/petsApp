package com.leverx.app.service.impl;

import com.leverx.app.entity.DTO.ResponseDTO;
import com.leverx.app.entity.DTO.ResponseListDTO;
import com.leverx.app.entity.cat.Cat;
import com.leverx.app.entity.dog.Dog;
import com.leverx.app.entity.user.User;
import com.leverx.app.service.CatService;
import com.leverx.app.service.CommonService;
import com.leverx.app.service.DogService;
import com.leverx.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

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
    public ResponseDTO createAll(User user, Cat cat, Dog dog) {

        Optional<User> userOptional = ofNullable(userService.create(user));
        if (userOptional.isPresent()) {
            dog.setUser(userOptional.get());
            cat.setUser(userOptional.get());
            return new ResponseDTO(
                    dogService.create(dog),
                    catService.create(cat),
                    userOptional.get());
        }else{
            return null;
        }
    }
}
