package com.leverx.app.transactional;

import com.leverx.app.entity.response.DTO.ResponseEntity;

public interface DogTransaction extends Transaction {

    @Override
    ResponseEntity add();

    @Override
    void rollback();
}
