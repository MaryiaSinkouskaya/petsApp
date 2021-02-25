package com.leverx.app.repository;

import com.leverx.app.entity.pet.Pet;

import java.util.List;

public interface PetRepository {
    List<Pet> findAll();
}
