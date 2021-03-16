package com.leverx.app.entity.request.dog;

import com.leverx.app.entity.request.pet.RequestPet;
import com.leverx.app.entity.response.dog.enums.PawColour;
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
