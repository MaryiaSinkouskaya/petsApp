package com.leverx.app.controller;

import com.leverx.app.entity.Pet;
import com.leverx.app.service.PetService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pet")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Pet> getPetById(@PathVariable(name = "id") long id){
        return petService.find(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Pet> getAllPets(){
        return petService.findAll();
    }

    @RequestMapping(value = "/user={id}", method = RequestMethod.GET)
    public List<Pet> getAllUserPets(@PathVariable(name = "id") long id){
        return petService.findAllByUserId(id);
    }

}
