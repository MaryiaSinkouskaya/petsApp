package com.leverx.app.service;

import com.leverx.app.entity.request.user.RequestUser;
import com.leverx.app.entity.response.user.ResponseUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<ResponseUser> findAll();

    ResponseUser create(RequestUser user);

    void delete(long id);

}
