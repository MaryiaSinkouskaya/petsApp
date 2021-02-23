package com.leverx.app.controller;

import com.leverx.app.entity.dog.Dog;
import com.leverx.app.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/dogs")
public class DogController {
    private final DogService dogService;

    @RequestMapping(value = "/{id}", method = GET)
    public Optional<Dog> getDogById(@PathVariable(name = "id") long id) {
        return dogService.find(id);
    }

    @RequestMapping(method = GET)
    public List<Dog> getAllDogs() {
        return dogService.findAll();
    }

    @RequestMapping(method = POST)
    public Dog createDog(@RequestBody Dog dog) {
        return dogService.create(dog);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void deleteDod(@PathVariable(name = "id") long id) {
        dogService.delete(id);
    }

    @RequestMapping(method = PUT)
    public Dog updateDog(@RequestBody Dog dog) {
        return dogService.update(dog);
    }

}
