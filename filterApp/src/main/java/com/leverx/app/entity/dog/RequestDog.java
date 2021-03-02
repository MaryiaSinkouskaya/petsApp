package com.leverx.app.entity.dog;

import com.leverx.app.entity.dog.enums.PawColour;
import com.leverx.app.entity.pet.RequestPet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDog extends RequestPet {

    private PawColour pawColour;

}
