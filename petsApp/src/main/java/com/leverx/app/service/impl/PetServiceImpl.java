package com.leverx.app.service.impl;

import com.leverx.app.entity.pet.Pet;
import com.leverx.app.repository.PetRepository;
import com.leverx.app.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    @Override
    public Optional<Pet> find(long id) {
        return petRepository.findById(id);
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> findAllByUserId(long id) {
        return petRepository.findAllByUserId(id);
    }

}
