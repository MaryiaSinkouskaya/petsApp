package com.leverx.app.service.odata.impl;

import com.leverx.app.config.odata.edm.PetEdm;
import com.leverx.app.config.odata.edm.UserEdm;
import com.leverx.app.repository.UserRepository;
import com.leverx.app.service.odata.UserServiceOdata;
import lombok.RequiredArgsConstructor;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertUser;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertUsers;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_PETS;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

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

    @Override
    public List<PetEdm> readRelatedData(Object sourceData, String targetEntityName) throws EdmException {
        UserEdm user = (UserEdm) sourceData;
        if (ENTITY_SET_NAME_PETS.equals(targetEntityName)) {
            return user.getPets();
        }
        throw new EdmException(ENTITY);
    }
}
