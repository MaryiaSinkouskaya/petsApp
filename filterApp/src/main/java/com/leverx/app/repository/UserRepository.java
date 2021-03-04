package com.leverx.app.repository;

import com.leverx.app.entity.request.user.RequestUser;
import com.leverx.app.entity.response.user.ResponseUser;

import java.util.List;

public interface UserRepository {

    List<ResponseUser> findAll();

    ResponseUser create(RequestUser user);

    void delete(long id);

}
