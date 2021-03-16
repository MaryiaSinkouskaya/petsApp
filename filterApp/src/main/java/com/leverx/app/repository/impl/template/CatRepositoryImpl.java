package com.leverx.app.repository.impl.template;

import com.leverx.app.entity.request.cat.RequestCat;
import com.leverx.app.entity.response.cat.ResponseCat;
import com.leverx.app.provider.authentification.AuthHeaderProvider;
import com.leverx.app.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
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
@Profile("template")
public class CatRepositoryImpl implements CatRepository {
    @Value(value = "${backend.cloud.server.url}")
    private final String backendUrl;
    @Value(value = "${cat.url}")
    private final String catUrl;
    private final AuthHeaderProvider authHeaderProvider;

    public List<ResponseCat> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseCat[]> responseEntity = restTemplate.exchange(
                backendUrl + catUrl,
                GET,
                new HttpEntity<>(authHeaderProvider.getAuthHeader()),
                ResponseCat[].class);
        return asList(requireNonNull(responseEntity.getBody()));
    }

    @Override
    public ResponseCat create(RequestCat cat) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                backendUrl + catUrl,
                POST,
                new HttpEntity<>(cat, authHeaderProvider.getAuthHeader()),
                ResponseCat.class).getBody();
    }

    @Override
    public void delete(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                backendUrl + catUrl + id,
                DELETE,
                new HttpEntity<>(authHeaderProvider.getAuthHeader()),
                ResponseCat.class);
    }
}
