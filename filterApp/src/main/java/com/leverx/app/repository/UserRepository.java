package com.leverx.app.repository;

import com.leverx.app.entity.user.RequestUser;
import com.leverx.app.entity.user.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User create(RequestUser user);

    void delete(long id);

}
