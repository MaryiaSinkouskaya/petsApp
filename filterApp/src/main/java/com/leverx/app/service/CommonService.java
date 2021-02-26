package com.leverx.app.service;

import com.leverx.app.entity.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommonService {
    ResponseDTO findAll();
}
