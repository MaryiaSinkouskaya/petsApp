package com.leverx.app.entity.response.pet;

import com.leverx.app.entity.response.user.ResponseUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ResponsePet {

    private long id;

    private String name;

    private ResponseUser user;
}
