package com.leverx.app.service;

import com.leverx.app.entity.Pet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PetService {
    Optional<Pet> find(long id);
    List<Pet> findAll();
    List<Pet> findAllByUserId(long id);

}
