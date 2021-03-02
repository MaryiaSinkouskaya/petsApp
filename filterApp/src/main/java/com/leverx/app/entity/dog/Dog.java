package com.leverx.app.entity.dog;

import com.leverx.app.entity.dog.enums.PawColour;
import com.leverx.app.entity.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dog extends Pet {

    private long id;

    private PawColour pawColour;
}
