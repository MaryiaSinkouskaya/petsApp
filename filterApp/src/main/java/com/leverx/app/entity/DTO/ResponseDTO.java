package com.leverx.app.entity.DTO;

import com.leverx.app.entity.cat.Cat;
import com.leverx.app.entity.dog.Dog;
import com.leverx.app.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDTO {
    private List<Cat> cats;
    private List<Dog> dogs;
    private List<User> users;
}
