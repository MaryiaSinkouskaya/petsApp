package com.leverx.app.repository;

import com.leverx.app.entity.cat.Cat;
import com.leverx.app.provider.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.web.client.HttpClientErrorException.Unauthorized;

@RequiredArgsConstructor
@Component
public class DogRepository {

    @Value(value = "${backend.server.url}")
    private String backendUrl;
    private final String dogUrl = "/api/dogs/";
    private final AuthProvider authProvider;


    public ResponseEntity<List> findAll() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.exchange(backendUrl + dogUrl,
                    GET, entityWithHeaders(authProvider.getAuth()), List.class);
        } catch (Unauthorized e) {
            return status(UNAUTHORIZED).build();
        } catch (HttpStatusCodeException e) {
            return status(NO_CONTENT).build();
        }
    }

    private HttpEntity<Cat> entityWithHeaders(String auth) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", auth);
        return new HttpEntity<>(headers);
    }

}
