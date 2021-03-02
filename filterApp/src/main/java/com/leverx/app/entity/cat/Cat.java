package com.leverx.app.entity.cat;

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
public class Cat extends Pet {

    private long id;

    private boolean clippedClaws;

}
