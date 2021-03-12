package com.leverx.app.transactional;

import com.leverx.app.dto.response.ResponseEntity;

public interface CatTransaction extends Transaction {

    @Override
    ResponseEntity save();

    @Override
    void rollback();
}
