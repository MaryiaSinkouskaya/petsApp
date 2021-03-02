package com.leverx.app.entity.cat;

import com.leverx.app.entity.pet.RequestPet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCat extends RequestPet {

    private boolean clippedClaws;

}
