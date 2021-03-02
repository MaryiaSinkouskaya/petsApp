package com.leverx.app.service.impl;

import com.leverx.app.entity.DTO.ResponseDTO;
import com.leverx.app.entity.DTO.ResponseListDTO;
import com.leverx.app.entity.cat.Cat;
import com.leverx.app.entity.cat.RequestCat;
import com.leverx.app.entity.dog.Dog;
import com.leverx.app.entity.dog.RequestDog;
import com.leverx.app.entity.user.RequestUser;
import com.leverx.app.entity.user.User;
import com.leverx.app.service.CatService;
import com.leverx.app.service.CommonService;
import com.leverx.app.service.DogService;
import com.leverx.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
@Log4j2
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
    public ResponseDTO createAll(RequestUser requestUser, RequestCat requestCat, RequestDog requestDog) {
        User user;
        Dog dog;
        Cat cat;
        try{
        user = userService.create(requestUser);
        }catch (HttpClientErrorException exception){
            log.error("CommonServiceImpl.createAll can't save user ", requestUser);
            throw new HttpClientErrorException(exception.getStatusCode());
        }catch (HttpServerErrorException exception){
            log.error("CommonServiceImpl.createAll can't save user ", requestUser);
            throw new HttpServerErrorException(exception.getStatusCode());
        }

        try{
            requestDog.setUser(user);
            dog = dogService.create(requestDog);
        }catch (HttpClientErrorException exception){
            userService.delete(user.getId());
            log.error("CommonServiceImpl.createAll can't save dog ", requestDog);
            throw new HttpClientErrorException(exception.getStatusCode());
        }catch (HttpServerErrorException exception){
            userService.delete(user.getId());
            log.error("CommonServiceImpl.createAll can't save dog ", requestDog);
            throw new HttpServerErrorException(exception.getStatusCode());
        }

        try{
            requestCat.setUser(user);
            cat = catService.create(requestCat);
        }catch (HttpClientErrorException exception){
            dogService.delete(dog.getId());
            userService.delete(user.getId());
            log.error("CommonServiceImpl.createAll can't save cat ", requestCat);
            throw new HttpClientErrorException(exception.getStatusCode());
        }catch (HttpServerErrorException exception){
            dogService.delete(dog.getId());
            userService.delete(user.getId());
            log.error("CommonServiceImpl.createAll can't save cat ", requestCat);
            throw new HttpServerErrorException(exception.getStatusCode());
        }

        return ResponseDTO.builder()
                .cat(cat)
                .dog(dog)
                .user(user)
                .build();
    }
}
