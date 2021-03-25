package com.leverx.app.entity.dog;

import com.leverx.app.entity.dog.enums.PawColour;
import com.leverx.app.entity.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.leverx.app.entity.constants.EntityConstants.ENTITY_CONTAINER;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_NAME_DOG;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_DOGS;
import static com.leverx.app.entity.constants.EntityConstants.NAMESPACE;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.INT64;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.STRING;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dog")
@EdmEntitySet(name = ENTITY_SET_NAME_DOGS, container = ENTITY_CONTAINER)
@EdmEntityType(name = ENTITY_NAME_DOG, namespace = NAMESPACE)
public class Dog extends Pet {

    @Id
    @Column(name = "id")
    @EdmKey
    @EdmProperty(type = INT64)
    private long id;

    @Column(name = "paw_colour")
    @Enumerated(EnumType.STRING)
    @EdmProperty(type = STRING)
    private PawColour pawColour;
}
