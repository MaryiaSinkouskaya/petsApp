package com.leverx.app.transactional;


import com.leverx.app.dto.response.ResponseEntity;

public interface Transaction {

    ResponseEntity save();

    void rollback();
}
