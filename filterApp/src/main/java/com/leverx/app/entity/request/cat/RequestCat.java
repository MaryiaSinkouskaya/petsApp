package com.leverx.app.entity.request.cat;

import com.leverx.app.entity.request.pet.RequestPet;
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
