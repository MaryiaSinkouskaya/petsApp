package com.leverx.app.entity.cat;

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
@Table(name = "cat")
public class Cat extends Pet {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "claws")
    private boolean clippedClaws;

    @Builder(builderMethodName = "catBuilder")
    public Cat(long id, boolean clippedClaws, String name, User user) {
        super(id, name, user);
        this.clippedClaws = clippedClaws;
    }
}
