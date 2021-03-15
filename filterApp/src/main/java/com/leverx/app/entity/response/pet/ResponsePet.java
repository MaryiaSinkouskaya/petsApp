package com.leverx.app.entity.response.pet;

import com.leverx.app.dto.response.ResponseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ResponsePet implements ResponseEntity {

    private long id;

    private String name;

}
