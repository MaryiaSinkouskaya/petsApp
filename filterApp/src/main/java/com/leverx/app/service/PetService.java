package com.leverx.app.service;

import com.leverx.app.entity.response.pet.ResponsePet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {

    List<ResponsePet> findAll();
}
