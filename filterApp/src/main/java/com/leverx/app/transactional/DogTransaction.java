package com.leverx.app.transactional;

import com.leverx.app.dto.response.ResponseEntity;

public interface DogTransaction extends Transaction {

    @Override
    ResponseEntity save();

    @Override
    void rollback();
}
