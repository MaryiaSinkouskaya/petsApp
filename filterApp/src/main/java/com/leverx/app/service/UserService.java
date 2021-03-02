package com.leverx.app.service;

import com.leverx.app.entity.user.RequestUser;
import com.leverx.app.entity.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();

    User create(RequestUser user);

    void delete(long id);

}
