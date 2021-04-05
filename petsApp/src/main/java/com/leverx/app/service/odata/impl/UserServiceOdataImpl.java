package com.leverx.app.service.odata.impl;

import com.leverx.app.edm.pet.PetEdm;
import com.leverx.app.edm.user.UserEdm;
import com.leverx.app.entity.user.User;
import com.leverx.app.service.jpa.UserService;
import com.leverx.app.service.odata.UserServiceOdata;
import lombok.RequiredArgsConstructor;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.leverx.app.edm.constants.EntityConstants.ENTITY_SET_NAME_PETS;
import static com.leverx.app.edm.mapper.EdmMapper.convertUser;
import static com.leverx.app.edm.mapper.EdmMapper.convertUsers;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

@RequiredArgsConstructor
@Service
public class UserServiceOdataImpl implements UserServiceOdata {

    private final UserService userService;

    @Override
    public UserEdm find(long id) {
        return convertUser(userService.find(id).get());
    }

    @Override
    public List<UserEdm> findAll() {
        return convertUsers(userService.findAll());
    }

    @Override
    public List<PetEdm> readRelatedData(Object sourceData, String targetEntityName) throws EdmException {
        UserEdm user = (UserEdm) sourceData;
        if (ENTITY_SET_NAME_PETS.equals(targetEntityName)) {
            return user.getPets();
        }
        throw new EdmException(ENTITY);
    }

    @Override
    public User save(UserEdm userEdm) {
        User user = convertUser(userEdm);
        return userService.create(user);
    }

    @Override
    public void delete(long id) {
        userService.delete(id);
    }

    @Override
    public UserEdm getNewEdm() {
        return new UserEdm();
    }
}
