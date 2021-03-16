package com.leverx.app.service.impl;

import com.leverx.app.entity.request.user.RequestUser;
import com.leverx.app.entity.response.user.ResponseUser;
import com.leverx.app.repository.UserRepository;
import com.leverx.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public ResponseUser create(RequestUser user) {
        return userRepository.create(user);
    }

    @Override
    public List<ResponseUser> findAll() {
        return userRepository.findAll();
    }
}
