package com.leverx.app.service;

import com.leverx.app.dto.request.RequestDto;
import com.leverx.app.dto.response.ResponseDto;
import com.leverx.app.dto.response.ResponseListDto;
import org.springframework.stereotype.Service;

@Service
public interface CommonService {

    ResponseListDto findAll();

    ResponseDto createAll(RequestDto requestDTO);
}
