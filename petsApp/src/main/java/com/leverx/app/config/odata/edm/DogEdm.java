package com.leverx.app.config.odata.edm;

import com.leverx.app.entity.dog.enums.PawColour;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import static com.leverx.app.entity.constants.EntityConstants.ENTITY_CONTAINER;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_NAME_DOG;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_DOGS;
import static com.leverx.app.entity.constants.EntityConstants.NAMESPACE;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.INT64;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.STRING;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EdmEntitySet(name = ENTITY_SET_NAME_DOGS, container = ENTITY_CONTAINER)
@EdmEntityType(name = ENTITY_NAME_DOG, namespace = NAMESPACE)
public class DogEdm extends PetEdm {

    @EdmKey
    @EdmProperty(type = INT64)
    private long id;

    @EdmProperty(type = STRING)
    private String name;

    @EdmProperty(type = STRING)
    private PawColour pawColour;
}
