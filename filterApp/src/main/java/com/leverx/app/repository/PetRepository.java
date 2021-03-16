package com.leverx.app.repository;

import com.leverx.app.entity.response.pet.ResponsePet;

import java.util.List;

public interface PetRepository {

    List<ResponsePet> findAll();
}
