package com.leverx.app.entity.DTO;

import com.leverx.app.entity.cat.Cat;
import com.leverx.app.entity.dog.Dog;
import com.leverx.app.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResponseDTO {
    private Dog dog;
    private Cat cat;
    private User user;
}
