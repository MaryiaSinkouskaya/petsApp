package com.leverx.app.controller;

import com.leverx.app.dto.request.RequestDto;
import com.leverx.app.dto.response.ResponseDto;
import com.leverx.app.dto.response.ResponseListDto;
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
    public ResponseListDto getAll() {
        return commonService.findAll();
    }

    @RequestMapping(method = POST)
    public ResponseDto setAll(@RequestBody RequestDto requestDTO) {
        return commonService.createAll(requestDTO);
    }
}
