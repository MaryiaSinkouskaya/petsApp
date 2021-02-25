package com.leverx.app.controller;

import com.leverx.app.provider.AuthProvider;
import com.leverx.app.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/all")
public class CommonController {

    private final CommonService commonService;
    private final AuthProvider authProvider;

    @RequestScope
    @RequestMapping(method = GET)
    public List getAll(@RequestHeader("Authorization") String auth) {
        authProvider.setAuth(auth);
        return commonService.findAll();
    }
}
