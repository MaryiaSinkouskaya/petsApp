package com.leverx.app.controller;

import com.leverx.app.entity.pet.Pet;
import com.leverx.app.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pets")
public class PetController {
    private final PetService petService;

    @RequestMapping(value = "/{id}", method = GET)
    public Optional<Pet> getPetById(@PathVariable(name = "id") long id) {
        return petService.find(id);
    }

    @RequestMapping(method = GET)
    public List<Pet> getAllPets() {
        return petService.findAll();
    }

    @RequestMapping(value = "/user={id}", method = GET)
    public List<Pet> getAllUserPets(@PathVariable(name = "id") long id) {
        return petService.findAllByUserId(id);
    }

}
