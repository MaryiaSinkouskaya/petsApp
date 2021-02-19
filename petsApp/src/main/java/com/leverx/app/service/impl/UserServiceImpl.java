package com.leverx.app.service.impl;

import com.leverx.app.entity.User;
import com.leverx.app.repository.UserRepository;
import com.leverx.app.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public Optional<User> find(long id) {
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
}
