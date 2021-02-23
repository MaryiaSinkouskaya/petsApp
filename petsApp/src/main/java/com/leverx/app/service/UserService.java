package com.leverx.app.service;

import com.leverx.app.entity.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> find(long id);

    Optional<User> findByName(String name);

    User create(User user);

    User update(User user);

    void delete(long id);

}
