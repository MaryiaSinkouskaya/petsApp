package com.leverx.app.transactional.impl;

import com.leverx.app.entity.request.dog.RequestDog;
import com.leverx.app.entity.response.DTO.ResponseEntity;
import com.leverx.app.entity.response.dog.ResponseDog;
import com.leverx.app.service.DogService;
import com.leverx.app.transactional.DogTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Data
@Builder
public class DogTransactionImpl implements DogTransaction {

    private final DogService dogService;
    private final RequestDog requestDog;
    private static final String ID = "dog_id";


    @Override
    public ResponseEntity add() {
        ResponseDog responseDog = dogService.create(requestDog);
        currentRequestAttributes().setAttribute(ID, responseDog.getId(),
                SCOPE_REQUEST);
        return responseDog;
    }

    @Override
    public void rollback() {
        long id = (long) currentRequestAttributes().getAttribute(ID,
                SCOPE_REQUEST);
        dogService.delete(id);
    }
}
