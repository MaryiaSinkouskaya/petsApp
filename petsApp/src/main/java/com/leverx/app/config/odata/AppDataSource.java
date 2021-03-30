package com.leverx.app.config.odata;

import com.leverx.app.config.odata.edm.CatEdm;
import com.leverx.app.config.odata.edm.DogEdm;
import com.leverx.app.config.odata.edm.PetEdm;
import com.leverx.app.config.odata.edm.UserEdm;
import com.leverx.app.service.odata.CatServiceOdata;
import com.leverx.app.service.odata.DogServiceOdata;
import com.leverx.app.service.odata.OdataCommonService;
import com.leverx.app.service.odata.PetServiceOdata;
import com.leverx.app.service.odata.UserServiceOdata;
import org.apache.olingo.odata2.annotation.processor.core.datasource.DataSource;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.edm.EdmFunctionImport;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.leverx.app.config.odata.AppContext.getApplicationContext;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_CATS;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_DOGS;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_PETS;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_USERS;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

public class AppDataSource implements DataSource {

    private final Map<String, OdataCommonService> map = new HashMap<>();

    public AppDataSource() {
        PetServiceOdata petService = getApplicationContext().getBean(PetServiceOdata.class);
        UserServiceOdata userService = getApplicationContext().getBean(UserServiceOdata.class);
        CatServiceOdata catService = getApplicationContext().getBean(CatServiceOdata.class);
        DogServiceOdata dogService = getApplicationContext().getBean(DogServiceOdata.class);
        map.put(ENTITY_SET_NAME_PETS, petService);
        map.put(ENTITY_SET_NAME_USERS, userService);
        map.put(ENTITY_SET_NAME_CATS, catService);
        map.put(ENTITY_SET_NAME_DOGS, dogService);
    }

    @Override
    public List<?> readData(EdmEntitySet entitySet) throws ODataNotFoundException, EdmException {
        String entitySetName = entitySet.getName();
        if (map.containsKey(entitySetName)) {
            return map.get(entitySetName).findAll();
        }
        throw new ODataNotFoundException(ENTITY);
    }

    @Override
    public Object readData(EdmEntitySet entitySet, Map<String, Object> keys) throws ODataNotFoundException, EdmException {
        String entitySetName = entitySet.getName();
        Long firstLayerEntityId = (Long) keys.get("Id");
        if (map.containsKey(entitySetName)) {
            return map.get(entitySetName).find(firstLayerEntityId);
        }
        throw new ODataNotFoundException(ENTITY);

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
                UserEdm user = (UserEdm) sourceData;
                if (ENTITY_SET_NAME_PETS.equals(targetEntityName)) {
                    return user.getPets();
                }
            case ENTITY_SET_NAME_PETS:
                PetEdm pet = (PetEdm) sourceData;
                if (ENTITY_SET_NAME_USERS.equals(targetEntityName)) {
                    return pet.getUser();
                }
            case ENTITY_SET_NAME_CATS:
                CatEdm cat = (CatEdm) sourceData;
                if (ENTITY_SET_NAME_USERS.equals(targetEntityName)) {
                    return cat.getUser();
                }
            case ENTITY_SET_NAME_DOGS:
                DogEdm dog = (DogEdm) sourceData;
                if (ENTITY_SET_NAME_USERS.equals(targetEntityName)) {
                    return dog.getUser();
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