package com.leverx.app.controller;

import com.leverx.app.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/all")
public class CommonController {

    private final CommonService commonService;

    @RequestMapping(method = GET)
    public List getAll(@RequestHeader("Authorization") String auth) {
        return commonService.findAll(auth);
    }
}
