package com.leverx.app.service;

import com.leverx.app.entity.pet.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {

    List<Pet> findAll();
}
