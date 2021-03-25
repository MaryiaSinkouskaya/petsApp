package com.leverx.app.entity.pet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.leverx.app.entity.user.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static com.leverx.app.entity.constants.EntityConstants.ASSOCIATION_USER_PET;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_CONTAINER;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_NAME_PET;
import static com.leverx.app.entity.constants.EntityConstants.ENTITY_SET_NAME_PETS;
import static com.leverx.app.entity.constants.EntityConstants.NAMESPACE;
import static com.leverx.app.entity.constants.EntityConstants.ROLE_PET;
import static org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty.Multiplicity.ONE;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.INT64;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.STRING;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pet")
@EdmEntitySet(name = ENTITY_SET_NAME_PETS, container = ENTITY_CONTAINER)
@EdmEntityType(name = ENTITY_NAME_PET, namespace = NAMESPACE)
public abstract class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EdmKey
    @EdmProperty(type = INT64)
    private long id;

    @Column(name = "name")
    @EdmProperty(type = STRING)
    private String name;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-pet")
    @EdmNavigationProperty(toMultiplicity = ONE,
            toType = User.class,
            association = ASSOCIATION_USER_PET,
            toRole = ROLE_PET)
    private User user;
}
