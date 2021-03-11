package com.leverx.app.controller;

import com.leverx.app.entity.request.DTO.RequestDTO;
import com.leverx.app.entity.response.DTO.ResponseDTO;
import com.leverx.app.entity.response.DTO.ResponseListDTO;
import com.leverx.app.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/all")
public class CommonController {

    private final CommonService commonService;

    @RequestMapping(method = GET)
    public ResponseListDTO getAll() {
        return commonService.findAll();
    }

    @RequestMapping(method = POST)
    public ResponseDTO setAll(@RequestBody RequestDTO requestDTO) {
        return commonService.createAll(requestDTO);
    }
}
