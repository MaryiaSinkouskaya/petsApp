package com.leverx.app.entity.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.leverx.app.entity.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import static javax.persistence.FetchType.*;
import static org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty.Multiplicity.MANY;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.INT64;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.STRING;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = EAGER)
    @JsonManagedReference(value = "user-pet")
    private List<Pet> pets;

}
