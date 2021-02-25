package com.leverx.app.repository;


import com.leverx.app.entity.cat.Cat;
import com.leverx.app.provider.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RequiredArgsConstructor
@Component
public class PetRepository {

    @Value(value = "${backend.server.url}")
    private String backendUrl;
    private final String petUrl = "/api/pets/";
    private final AuthProvider authProvider;


    public ResponseEntity<List> findAll() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.exchange(backendUrl + petUrl,
                    GET, entityWithHeaders(authProvider.getAuth()), List.class);
        } catch (HttpClientErrorException.Unauthorized e) {
            return ResponseEntity.status(UNAUTHORIZED).build();
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
