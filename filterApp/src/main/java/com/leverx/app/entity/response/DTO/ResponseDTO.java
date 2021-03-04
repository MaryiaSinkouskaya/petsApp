package com.leverx.app.entity.response.DTO;

import com.leverx.app.entity.response.cat.ResponseCat;
import com.leverx.app.entity.response.dog.ResponseDog;
import com.leverx.app.entity.response.user.ResponseUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private ResponseUser user;
    private ResponseCat cat;
    private ResponseDog dog;
}
