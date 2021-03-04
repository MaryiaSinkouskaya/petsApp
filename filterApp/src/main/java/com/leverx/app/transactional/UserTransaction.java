package com.leverx.app.transactional;

import com.leverx.app.entity.response.DTO.ResponseEntity;

public interface UserTransaction extends Transaction {

    @Override
    ResponseEntity add();

    @Override
    void rollback();
}
