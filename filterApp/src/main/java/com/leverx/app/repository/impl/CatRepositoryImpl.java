package com.leverx.app.repository.impl;

import com.leverx.app.entity.cat.Cat;
import com.leverx.app.provider.AuthProvider;
import com.leverx.app.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@RequiredArgsConstructor
@Component
public class CatRepositoryImpl implements CatRepository {

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    @Value(value = "${cat.url}")
    private final String catUrl;
    private final AuthProvider authProvider;

    public List<Cat> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String auth = authProvider.getAuth();
        headers.set("Authorization", auth);
        ResponseEntity<Cat[]> responseEntity = restTemplate
                .exchange(backendUrl + catUrl, GET, new HttpEntity<>(headers), Cat[].class);
        return asList(requireNonNull(responseEntity.getBody()));
    }

    @Override
    public Cat create(Cat cat) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String auth = authProvider.getAuth();
        headers.set("Authorization", auth);
        return restTemplate
                .exchange(backendUrl + catUrl, POST, new HttpEntity<>(cat, headers), Cat.class)
                .getBody();
    }
}
