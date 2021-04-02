package com.leverx.app.edm.pet;

import com.leverx.app.edm.user.UserEdm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import static com.leverx.app.edm.constants.EntityConstants.ASSOCIATION_USER_PET;
import static com.leverx.app.edm.constants.EntityConstants.ENTITY_CONTAINER;
import static com.leverx.app.edm.constants.EntityConstants.ENTITY_NAME_PET;
import static com.leverx.app.edm.constants.EntityConstants.ENTITY_SET_NAME_PETS;
import static com.leverx.app.edm.constants.EntityConstants.NAMESPACE;
import static com.leverx.app.edm.constants.EntityConstants.ROLE_PET;
import static org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty.Multiplicity.ONE;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.INT64;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.STRING;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EdmEntitySet(name = ENTITY_SET_NAME_PETS, container = ENTITY_CONTAINER)
@EdmEntityType(name = ENTITY_NAME_PET, namespace = NAMESPACE)
public class PetEdm {

    @EdmKey
    @EdmProperty(type = INT64)
    private long id;

    @EdmProperty(type = STRING)
    private String name;

    @EdmNavigationProperty(toMultiplicity = ONE,
            toType = UserEdm.class,
            association = ASSOCIATION_USER_PET,
            toRole = ROLE_PET)
    private UserEdm user;
}
