package com.leverx.app.service;

import com.leverx.app.entity.DTO.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface CommonService {
    ResponseDTO findAll();
}
