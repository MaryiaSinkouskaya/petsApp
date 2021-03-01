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

        Optional<User> responseUser = ofNullable(userService.create(user));
        if (responseUser.isPresent()) {
            dog.setUser(responseUser.get());
            Optional<Dog> responseDog = ofNullable(dogService.create(dog));
            if (responseDog.isPresent()) {
                cat.setUser(responseUser.get());
                Optional<Cat> responseCat = ofNullable(catService.create(cat));
                if (responseCat.isPresent()) {
                    return new ResponseDTO(
                            responseDog.get(),
                            responseCat.get(),
                            responseUser.get());
                } else {
                    dogService.delete(responseDog.get().getId());
                    userService.delete(responseUser.get().getId());
                }
            } else {
                userService.delete(responseUser.get().getId());
            }
        }
        throw new NullPointerException();
    }
}
