package com.leverx.app.service;

import com.leverx.app.entity.response.pet.ResponsePet;

import java.util.List;

public interface PetService {

    List<ResponsePet> findAll();
}
