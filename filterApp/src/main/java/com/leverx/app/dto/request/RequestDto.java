package com.leverx.app.dto.request;

import com.leverx.app.entity.request.cat.RequestCat;
import com.leverx.app.entity.request.dog.RequestDog;
import com.leverx.app.entity.request.user.RequestUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestDto {

    private RequestCat cat;
    private RequestDog dog;
    private RequestUser user;

}
