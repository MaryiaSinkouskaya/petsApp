package com.leverx.app.service.odata.impl;

import com.leverx.app.config.odata.edm.UserEdm;
import com.leverx.app.repository.UserRepository;
import com.leverx.app.service.odata.UserServiceOdata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertUser;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertUsers;

@RequiredArgsConstructor
@Service
public class UserServiceOdataImpl implements UserServiceOdata {

    private final UserRepository userRepository;

    @Override
    public UserEdm find(long id) {
        return convertUser(userRepository.findById(id).get());
    }

    @Override
    public List<UserEdm> findAll() {
        return convertUsers(userRepository.findAll());
    }
}
