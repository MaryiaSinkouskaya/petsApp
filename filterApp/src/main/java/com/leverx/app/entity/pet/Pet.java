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
public abstract class Pet {

    private long id;

    private String name;

    private User user;
}
