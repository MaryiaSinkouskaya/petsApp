package com.leverx.app.entity.cat;

import com.leverx.app.entity.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cat")
public class Cat extends Pet {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "claws")
    private boolean clippedClaws;
}
