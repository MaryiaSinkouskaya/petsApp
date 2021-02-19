package com.leverx.app.service.impl;

import com.leverx.app.entity.Pet;
import com.leverx.app.repository.PetRepository;
import com.leverx.app.service.PetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Transactional
    @Override
    public Optional<Pet> find(long id) {
        return petRepository.findById(id);
    }

    @Transactional
    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Transactional
    @Override
    public List<Pet> findAllByUserId(long id) {
        return petRepository.findAllByUserId(id);
    }

}
