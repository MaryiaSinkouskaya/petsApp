package com.leverx.app.service.impl;

import com.leverx.app.entity.cat.Cat;
import com.leverx.app.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequiredArgsConstructor
@Service
public class CatServiceImpl implements CatService {

    @Value(value = "${backend.server.url}")
    private String backendUrl;
    private final String catUrl = "/api/cats/";

    @Override
    public ResponseEntity<List> findAll(String auth) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.exchange(backendUrl + catUrl,
                    GET, entityWithHeaders(auth), List.class);
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(NO_CONTENT).build();
        }
    }

    private HttpEntity<Cat> entityWithHeaders(String auth) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", auth);
        return new HttpEntity<>(headers);
    }

}
