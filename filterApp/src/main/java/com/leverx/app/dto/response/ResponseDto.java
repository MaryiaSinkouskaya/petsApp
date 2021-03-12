package com.leverx.app.dto.response;

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
public class ResponseDto {

    private ResponseUser user;
    private ResponseCat cat;
    private ResponseDog dog;

    public ResponseDto add(ResponseEntity entity) {
        if (entity.getClass().equals(ResponseUser.class)) {
            this.setUser((ResponseUser) entity);
        } else if (entity.getClass().equals(ResponseCat.class)) {
            this.setCat((ResponseCat) entity);
        } else if (entity.getClass().equals(ResponseDog.class)) {
            this.setDog((ResponseDog) entity);
        }
        return this;
    }
}
