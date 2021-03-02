package com.leverx.app.repository.impl;

import com.leverx.app.entity.dog.Dog;
import com.leverx.app.entity.dog.RequestDog;
import com.leverx.app.provider.AuthHeaderProvider;
import com.leverx.app.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@RequiredArgsConstructor
@Component
public class DogRepositoryImpl implements DogRepository {

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    @Value(value = "${dog.url}")
    private final String dogUrl;
    private final AuthHeaderProvider authHeaderProvider;

    public List<Dog> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(authHeaderProvider.getAuthHeader());
        ResponseEntity<Dog[]> responseEntity = restTemplate.exchange(
                backendUrl + dogUrl,
                GET,
                httpEntity,
                Dog[].class);
        return asList(requireNonNull(responseEntity.getBody()));
    }

    @Override
    public Dog create(RequestDog dog) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RequestDog> httpEntity = new HttpEntity<>(dog, authHeaderProvider.getAuthHeader());
        return restTemplate.exchange(
                backendUrl + dogUrl,
                POST,
                httpEntity,
                Dog.class).getBody();
    }

    @Override
    public void delete(long id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(authHeaderProvider.getAuthHeader());
        restTemplate.exchange(
                backendUrl + dogUrl + id,
                DELETE,
                httpEntity,
                Dog.class);
    }
}
