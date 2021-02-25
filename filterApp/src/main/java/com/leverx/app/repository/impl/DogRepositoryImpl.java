package com.leverx.app.repository.impl;

import com.leverx.app.entity.dog.Dog;
import com.leverx.app.provider.AuthProvider;
import com.leverx.app.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpMethod.GET;

@RequiredArgsConstructor
@Component
public class DogRepositoryImpl implements DogRepository {

    @Value(value = "${backend.server.url}")
    private String backendUrl;
    @Value(value = "${dog.url}")
    private String dogUrl;
    private final AuthProvider authProvider;

    public List<Dog> findAll() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Dog[]> responseEntity = restTemplate
                    .exchange(backendUrl + dogUrl, GET, entityWithHeaders(), Dog[].class);
            return asList(requireNonNull(responseEntity.getBody()));
        } catch (HttpStatusCodeException e) {
            return emptyList();
        }
    }

    private HttpEntity<Dog> entityWithHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String auth = authProvider.getAuth();
        headers.set("Authorization", auth);
        return new HttpEntity<>(headers);
    }
}
