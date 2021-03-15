package com.leverx.app.entity.response.cat;

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
public class ResponseCat extends ResponsePet {

    private long id;

    private boolean clippedClaws;

}
