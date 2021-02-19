package com.leverx.app.controller;

import com.leverx.app.entity.User;
import com.leverx.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @RequestMapping(value = "/{id}", method = GET)
    public Optional<User> getUserById(@PathVariable(name = "id") long id) {
        return userService.find(id);
    }

    @RequestMapping(method = POST)
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @RequestMapping(method = PUT)
    public User updateUser(@RequestBody User user) {
        return userService.update(user);
    }

}
