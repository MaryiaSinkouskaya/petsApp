package com.leverx.app.config.odata.edm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import static com.leverx.app.entity.constants.EntityConstants.ENTITY_CONTAINER;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_NAME_CAT;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_CATS;
import static com.leverx.app.entity.constants.EntityConstants.NAMESPACE;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.STRING;

@Getter
@Setter
@NoArgsConstructor
@EdmEntitySet(name = ENTITY_SET_NAME_CATS, container = ENTITY_CONTAINER)
@EdmEntityType(name = ENTITY_NAME_CAT, namespace = NAMESPACE)
public class CatEdm extends PetEdm {

    @EdmProperty(type = STRING)
    private boolean clippedClaws;

    public CatEdm(long id, String name, UserEdm userEdm, boolean clippedClaws) {
        super(id, name, userEdm);
        this.clippedClaws = clippedClaws;
    }
}
