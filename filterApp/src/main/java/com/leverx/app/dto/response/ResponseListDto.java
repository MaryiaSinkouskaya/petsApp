package com.leverx.app.dto.response;

import com.leverx.app.entity.response.cat.ResponseCat;
import com.leverx.app.entity.response.dog.ResponseDog;
import com.leverx.app.entity.response.user.ResponseUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseListDto {

    private List<ResponseDog> dogs;
    private List<ResponseCat> cats;
    private List<ResponseUser> users;
}
