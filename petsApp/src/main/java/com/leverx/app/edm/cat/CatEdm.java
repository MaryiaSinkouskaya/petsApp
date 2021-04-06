package com.leverx.app.edm.cat;

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
import static com.leverx.app.edm.constants.EntityConstants.ENTITY_NAME_CAT;
import static com.leverx.app.edm.constants.EntityConstants.ENTITY_SET_NAME_CATS;
import static com.leverx.app.edm.constants.EntityConstants.NAMESPACE;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.BOOLEAN;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.INT64;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EdmEntitySet(name = ENTITY_SET_NAME_CATS, container = ENTITY_CONTAINER)
@EdmEntityType(name = ENTITY_NAME_CAT, namespace = NAMESPACE)
public class CatEdm extends PetEdm {

    @EdmProperty(type = BOOLEAN)
    private Boolean clippedClaws;

    @EdmProperty(type = INT64)
    private Long userId;

    @Builder(builderMethodName = "catEdmBuilder")
    public CatEdm(long id, String name, UserEdm userEdm, Boolean clippedClaws, long userId) {
        super(id, name, userEdm);
        this.clippedClaws = clippedClaws;
        this.userId = userId;
    }
}
