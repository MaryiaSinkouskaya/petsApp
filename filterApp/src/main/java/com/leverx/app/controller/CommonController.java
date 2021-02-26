package com.leverx.app.controller;

import com.leverx.app.entity.DTO.ResponseDTO;
import com.leverx.app.entity.DTO.ResponseListDTO;
import com.leverx.app.provider.AuthProvider;
import com.leverx.app.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/all")
public class CommonController {

    private final CommonService commonService;
    private final AuthProvider authProvider;

    @RequestScope
    @RequestMapping(method = GET)
    public ResponseListDTO getAll(@RequestHeader("Authorization") String auth) {
        authProvider.setAuth(auth);
        return commonService.findAll();
    }

    @RequestScope
    @RequestMapping(method = POST)
    public ResponseDTO setAll(@RequestHeader("Authorization") String auth,
                              @RequestBody ResponseDTO responseDTO) {
        authProvider.setAuth(auth);
        return commonService.createAll(
                responseDTO.getUser(),
                responseDTO.getCat(),
                responseDTO.getDog());
    }

}
