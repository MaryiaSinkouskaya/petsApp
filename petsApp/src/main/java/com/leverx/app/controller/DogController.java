package com.leverx.app.controller;

import com.leverx.app.entity.Dog;
import com.leverx.app.service.DogService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dog")
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Dog> getDogById(@PathVariable(name = "id") long id){
        return dogService.find(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Dog> getAllDogs(){
        return dogService.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Dog createDog(@RequestBody Dog dog) {
        return dogService.create(dog);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void deleteDod(@RequestBody Dog dog){
        dogService.delete(dog);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Dog updateDog(@RequestBody Dog dog) {
        return dogService.update(dog);
    }

}
