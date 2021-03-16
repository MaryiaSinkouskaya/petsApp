package com.leverx.app.transactional.builder;

import com.leverx.app.entity.request.cat.RequestCat;
import com.leverx.app.entity.request.dog.RequestDog;
import com.leverx.app.entity.request.user.RequestUser;
import com.leverx.app.service.CatService;
import com.leverx.app.service.DogService;
import com.leverx.app.service.UserService;
import com.leverx.app.transactional.impl.CatTransactionImpl;
import com.leverx.app.transactional.impl.DogTransactionImpl;
import com.leverx.app.transactional.impl.UserTransactionImpl;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class TransactionalBuilder {

    public static UserTransactionImpl buildUserTransaction(RequestUser requestUser, UserService service) {
        return UserTransactionImpl
                .builder()
                .userService(service)
                .requestUser(requestUser)
                .build();
    }

    public static CatTransactionImpl buildCatTransaction(RequestCat requestCat, CatService service) {
        return CatTransactionImpl
                .builder()
                .catService(service)
                .requestCat(requestCat)
                .build();
    }

    public static DogTransactionImpl buildDogTransaction(RequestDog requestDog, DogService service) {
        return DogTransactionImpl
                .builder()
                .dogService(service)
                .requestDog(requestDog)
                .build();
    }

}