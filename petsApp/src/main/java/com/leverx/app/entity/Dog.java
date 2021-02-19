package com.leverx.app.entity;

import com.leverx.app.entity.enums.PawColour;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dog", schema = "pet_schema")
public class Dog extends Pet {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "paw_colour")
    @Enumerated(EnumType.STRING)
    private PawColour pawColour;
}
