package com.leverx.app.transactional;


import com.leverx.app.entity.response.DTO.ResponseEntity;

public interface Transaction {

    ResponseEntity add();

    void rollback();
}
