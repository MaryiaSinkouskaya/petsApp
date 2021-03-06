package com.leverx.app.service.impl;

import com.leverx.app.entity.response.pet.ResponsePet;
import com.leverx.app.repository.PetRepository;
import com.leverx.app.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    public List<ResponsePet> findAll() {
        return petRepository.findAll();
    }
}
