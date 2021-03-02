package com.leverx.app.entity.dog;

import com.leverx.app.entity.dog.enums.PawColour;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDog {

    private PawColour pawColour;

}
