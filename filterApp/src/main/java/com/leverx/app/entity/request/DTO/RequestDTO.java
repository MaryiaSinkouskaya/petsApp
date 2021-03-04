package com.leverx.app.entity.request.DTO;

import com.leverx.app.entity.request.cat.RequestCat;
import com.leverx.app.entity.request.dog.RequestDog;
import com.leverx.app.entity.request.user.RequestUser;
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
