package com.leverx.app.entity.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.leverx.app.entity.pet.Pet;
import lombok.Getter;
import lombok.Setter;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

@Entity
@Getter
@Setter
@Table(name = "user")
@EdmEntitySet(name = ENTITY_SET_NAME_USERS, container = ENTITY_CONTAINER)
@EdmEntityType(name = ENTITY_NAME_USER, namespace = NAMESPACE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EdmKey
    @EdmProperty(type = INT64)
    private long id;

    @Column(name = "name")
    @EdmProperty(type = STRING)
    private String name;

    @Column(name = "password")
    @EdmProperty(type = STRING)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-pet")
    @EdmNavigationProperty(toMultiplicity = MANY,
            toType = Pet.class,
            association = ASSOCIATION_USER_PET,
            toRole = ROLE_PET)
    private List<Pet> pets;

}
