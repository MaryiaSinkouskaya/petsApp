package com.leverx.app.repository.impl;

import com.leverx.app.entity.request.cat.RequestCat;
import com.leverx.app.entity.response.cat.ResponseCat;
import com.leverx.app.provider.AuthHeaderProvider;
import com.leverx.app.repository.CatRepository;
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
public class CatRepositoryImpl implements CatRepository {

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    @Value(value = "${cat.url}")
    private final String catUrl;
    private final AuthHeaderProvider authHeaderProvider;

    public List<ResponseCat> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(authHeaderProvider.getAuthHeader());
        ResponseEntity<ResponseCat[]> responseEntity = restTemplate.exchange(
                backendUrl + catUrl,
                GET,
                httpEntity,
                ResponseCat[].class);
        return asList(requireNonNull(responseEntity.getBody()));
    }

    @Override
    public ResponseCat create(RequestCat cat) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RequestCat> httpEntity = new HttpEntity<>(cat, authHeaderProvider.getAuthHeader());
        return restTemplate.exchange(
                backendUrl + catUrl,
                POST,
                httpEntity,
                ResponseCat.class).getBody();
    }

    @Override
    public void delete(long id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(authHeaderProvider.getAuthHeader());
        restTemplate.exchange(
                backendUrl + catUrl + id,
                DELETE,
                httpEntity,
                ResponseCat.class);
    }
}
