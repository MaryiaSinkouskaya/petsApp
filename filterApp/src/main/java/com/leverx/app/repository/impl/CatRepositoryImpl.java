package com.leverx.app.repository.impl;

import com.leverx.app.entity.cat.Cat;
import com.leverx.app.entity.cat.RequestCat;
import com.leverx.app.provider.AuthHeaderProvider;
import com.leverx.app.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@RequiredArgsConstructor
@Component
public class CatRepositoryImpl implements CatRepository {

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    @Value(value = "${cat.url}")
    private final String catUrl;
    private final AuthHeaderProvider authHeaderProvider;

    public List<Cat> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cat[]> responseEntity = restTemplate.exchange(
                backendUrl + catUrl ,
                GET,
                new HttpEntity<>(authHeaderProvider.getAuthHeader()),
                Cat[].class);
        return asList(requireNonNull(responseEntity.getBody()));
    }

    @Override
    public Cat create(RequestCat cat) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                backendUrl + catUrl,
                POST,
                new HttpEntity<>(cat, authHeaderProvider.getAuthHeader()),
                Cat.class).getBody();
    }

    @Override
    public void delete(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                backendUrl + catUrl + id,
                DELETE,
                new HttpEntity<>(authHeaderProvider.getAuthHeader()),
                Cat.class);
    }
}
