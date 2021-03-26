package com.leverx.app.config.odata;

import com.leverx.app.entity.cat.Cat;
import com.leverx.app.entity.dog.Dog;
import com.leverx.app.entity.pet.Pet;
import com.leverx.app.entity.user.User;
import com.leverx.app.service.CatService;
import com.leverx.app.service.DogService;
import com.leverx.app.service.PetService;
import com.leverx.app.service.UserService;
import org.apache.olingo.odata2.annotation.processor.core.datasource.DataSource;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.edm.EdmFunctionImport;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import java.util.List;
import java.util.Map;

import static com.leverx.app.config.odata.AppContext.getApplicationContext;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertCat;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertCats;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertDog;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertDogs;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertPet;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertPets;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertUser;
import static com.leverx.app.config.odata.edm.mapper.EdmMapper.convertUsers;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_CATS;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_DOGS;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_PETS;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_USERS;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

public class AppDataSource implements DataSource {

    private final PetService petService;
    private final UserService userService;
    private final CatService catService;
    private final DogService dogService;

    public AppDataSource() {
        petService = getApplicationContext().getBean(PetService.class);
        userService = getApplicationContext().getBean(UserService.class);
        catService = getApplicationContext().getBean(CatService.class);
        dogService = getApplicationContext().getBean(DogService.class);
    }

    @Override
    public List<?> readData(EdmEntitySet entitySet) throws ODataNotFoundException, EdmException {
        String entitySetName = entitySet.getName();
        switch (entitySetName) {
            case ENTITY_SET_NAME_PETS:
                return convertPets(petService.findAll());
            case ENTITY_SET_NAME_CATS:
                return convertCats(catService.findAll());
            case ENTITY_SET_NAME_DOGS:
                return convertDogs(dogService.findAll());
            case ENTITY_SET_NAME_USERS:
                return convertUsers(userService.findAll());
            default:
                throw new ODataNotFoundException(ENTITY);
        }
    }

    @Override
    public Object readData(EdmEntitySet entitySet, Map<String, Object> keys) throws ODataNotFoundException, EdmException {
        String entitySetName = entitySet.getName();
        Long firstLayerEntityId = (Long) keys.get("Id");
        switch (entitySetName) {
            case ENTITY_SET_NAME_PETS:
                return convertPet(petService.find(firstLayerEntityId).get());
            case ENTITY_SET_NAME_CATS:
                return convertCat(catService.find(firstLayerEntityId).get());
            case ENTITY_SET_NAME_DOGS:
                return convertDog(dogService.find(firstLayerEntityId).get());
            case ENTITY_SET_NAME_USERS:
                return convertUser(userService.find(firstLayerEntityId).get());
            default:
                throw new ODataNotFoundException(ENTITY);
        }
    }

    @Override
    public Object readData(EdmFunctionImport function,
                           Map<String, Object> parameters,
                           Map<String, Object> keys) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public Object readRelatedData(EdmEntitySet sourceEntitySet,
                                  Object sourceData,
                                  EdmEntitySet targetEntitySet,
                                  Map<String, Object> targetKeys) throws EdmException {
        String sourceEntityName = sourceEntitySet.getName();
        String targetEntityName = targetEntitySet.getName();
        switch (sourceEntityName) {
            case ENTITY_SET_NAME_USERS:
                User user = (User) sourceData;
                if (ENTITY_SET_NAME_PETS.equals(targetEntityName)) {
                    return convertPets(user.getPets());
                }
            case ENTITY_SET_NAME_PETS:
                Pet pet = (Pet) sourceData;
                if (ENTITY_SET_NAME_USERS.equals(targetEntityName)) {
                    return convertUser(pet.getUser());
                }
            case ENTITY_SET_NAME_CATS:
                Cat cat = (Cat) sourceData;
                if (ENTITY_SET_NAME_USERS.equals(targetEntityName)) {
                    return convertUser(cat.getUser());
                }
            case ENTITY_SET_NAME_DOGS:
                Dog dog = (Dog) sourceData;
                if (ENTITY_SET_NAME_USERS.equals(targetEntityName)) {
                    return convertUser(dog.getUser());
                }
        }
        throw new EdmException(ENTITY);
    }

    @Override
    public BinaryData readBinaryData(EdmEntitySet entitySet, Object mediaLinkEntryData) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public Object newDataObject(EdmEntitySet entitySet) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public void writeBinaryData(EdmEntitySet entitySet, Object mediaLinkEntryData, BinaryData binaryData) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public void deleteData(EdmEntitySet entitySet, Map<String, Object> keys) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public void createData(EdmEntitySet entitySet, Object data) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public void deleteRelation(EdmEntitySet sourceEntitySet, Object sourceData, EdmEntitySet targetEntitySet, Map<String, Object> targetKeys) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public void writeRelation(EdmEntitySet sourceEntitySet, Object sourceData, EdmEntitySet targetEntitySet, Map<String, Object> targetKeys) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }
}