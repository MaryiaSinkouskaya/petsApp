package com.leverx.app.service;

import com.leverx.app.entity.request.DTO.RequestDTO;
import com.leverx.app.entity.response.DTO.ResponseDTO;
import com.leverx.app.entity.response.DTO.ResponseListDTO;
import org.springframework.stereotype.Service;

@Service
public interface CommonService {

    ResponseListDTO findAll();

    ResponseDTO createAll(RequestDTO requestDTO);
}
