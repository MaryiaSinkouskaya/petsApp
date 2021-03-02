package com.leverx.app.service.impl;

import com.leverx.app.entity.user.RequestUser;
import com.leverx.app.entity.user.User;
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
    public User create(RequestUser user) {
        return userRepository.create(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
