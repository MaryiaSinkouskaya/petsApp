package com.leverx.app.entity.pet;

import com.leverx.app.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class RequestPet {

    private String name;

    private User user;

}
