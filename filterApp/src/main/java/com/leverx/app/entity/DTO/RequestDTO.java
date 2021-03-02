package com.leverx.app.entity.DTO;

import com.leverx.app.entity.cat.RequestCat;
import com.leverx.app.entity.dog.RequestDog;
import com.leverx.app.entity.user.RequestUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestDTO {

    private RequestCat cat;
    private RequestDog dog;
    private RequestUser user;

}
