package com.leverx.app.edm.dog;

import com.leverx.app.edm.pet.PetEdm;
import com.leverx.app.edm.user.UserEdm;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import static com.leverx.app.edm.constants.EntityConstants.ENTITY_CONTAINER;
import static com.leverx.app.edm.constants.EntityConstants.ENTITY_NAME_DOG;
import static com.leverx.app.edm.constants.EntityConstants.ENTITY_SET_NAME_DOGS;
import static com.leverx.app.edm.constants.EntityConstants.NAMESPACE;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.INT64;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.STRING;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EdmEntitySet(name = ENTITY_SET_NAME_DOGS, container = ENTITY_CONTAINER)
@EdmEntityType(name = ENTITY_NAME_DOG, namespace = NAMESPACE)
public class DogEdm extends PetEdm {

    @EdmProperty(type = STRING)
    private String pawColour;

    @EdmProperty(type = INT64)
    private Long userId;

    @Builder(builderMethodName = "dogEdmBuilder")
    public DogEdm(long id, String name, UserEdm userEdm, String pawColour, long userId) {
        super(id, name, userEdm);
        this.pawColour = pawColour;
        this.userId = userId;
    }
}
