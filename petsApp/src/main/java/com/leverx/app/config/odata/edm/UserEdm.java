package com.leverx.app.config.odata.edm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import java.util.List;

import static com.leverx.app.entity.constants.EntityConstants.ASSOCIATION_USER_PET;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_CONTAINER;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_NAME_USER;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_USERS;
import static com.leverx.app.entity.constants.EntityConstants.NAMESPACE;
import static com.leverx.app.entity.constants.EntityConstants.ROLE_PET;
import static org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty.Multiplicity.MANY;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.INT64;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.STRING;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EdmEntitySet(name = ENTITY_SET_NAME_USERS, container = ENTITY_CONTAINER)
@EdmEntityType(name = ENTITY_NAME_USER, namespace = NAMESPACE)
public class UserEdm {

    @EdmKey
    @EdmProperty(type = INT64)
    private long id;

    @EdmProperty(type = STRING)
    private String name;

    @EdmProperty(type = STRING)
    private String password;

    @EdmNavigationProperty(toMultiplicity = MANY,
            toType = PetEdm.class,
            association = ASSOCIATION_USER_PET,
            toRole = ROLE_PET)
    private List<PetEdm> pets;
}
