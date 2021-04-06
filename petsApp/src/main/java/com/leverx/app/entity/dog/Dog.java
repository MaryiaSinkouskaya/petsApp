package com.leverx.app.entity.dog;

import com.leverx.app.entity.dog.enums.PawColour;
import com.leverx.app.entity.pet.Pet;
import com.leverx.app.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dog")
public class Dog extends Pet {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "paw_colour")
    private PawColour pawColour;

    @Builder(builderMethodName = "dogBuilder")
    public Dog(long id, PawColour pawColour, String name, User user) {
        super(id, name, user);
        this.pawColour = pawColour;
    }

}
