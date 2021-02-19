package com.leverx.app.service;

import com.leverx.app.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> find(long id);
    User create(User user);
    User update(User user);
}
