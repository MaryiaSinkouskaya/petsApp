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
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@RequiredArgsConstructor
@Component
public class DogRepositoryImpl implements DogRepository {

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    @Value(value = "${dog.url}")
    private final String dogUrl;
    private final AuthProvider authProvider;

    public List<Dog> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String auth = authProvider.getAuth();
        headers.set("Authorization", auth);

        ResponseEntity<Dog[]> responseEntity = restTemplate
                .exchange(backendUrl + dogUrl, GET, new HttpEntity<>(headers), Dog[].class);
            return asList(requireNonNull(responseEntity.getBody()));
    }

    @Override
    public Dog create(Dog dog) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String auth = authProvider.getAuth();
        headers.set("Authorization", auth);
        return restTemplate
                .exchange(backendUrl + dogUrl, POST, new HttpEntity<>(dog, headers), Dog.class)
                .getBody();
    }
}
