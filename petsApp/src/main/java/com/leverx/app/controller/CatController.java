package com.leverx.app.controller;

import com.leverx.app.entity.Cat;
import com.leverx.app.service.CatService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cat")
public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Cat> getCatById(@PathVariable(name = "id") long id) {
        return catService.find(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Cat> getAllCats() {
        return catService.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Cat createCat(@RequestBody Cat cat) {
        return catService.create(cat);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void deleteCat(@RequestBody Cat cat){
        catService.delete(cat);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Cat updateCat(@RequestBody Cat cat) {
        return catService.update(cat);
    }

}
