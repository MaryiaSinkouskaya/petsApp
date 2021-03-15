package com.leverx.app.entity.response.dog;

import com.leverx.app.dto.response.ResponseEntity;
import com.leverx.app.entity.response.dog.enums.PawColour;
import com.leverx.app.entity.response.pet.ResponsePet;
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
public class ResponseDog extends ResponsePet {

    private long id;

    private PawColour pawColour;
}
