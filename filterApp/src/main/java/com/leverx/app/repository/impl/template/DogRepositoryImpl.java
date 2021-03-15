package com.leverx.app.repository.impl.template;

import com.leverx.app.entity.request.dog.RequestDog;
import com.leverx.app.entity.response.dog.ResponseDog;
import com.leverx.app.provider.authentification.AuthHeaderProvider;
import com.leverx.app.repository.DogRepository;
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
public class DogRepositoryImpl implements DogRepository {

    @Value(value = "${backend.cloud.server.url}")
    private final String backendUrl;
    @Value(value = "${dog.url}")
    private final String dogUrl;
    private final AuthHeaderProvider authHeaderProvider;

    public List<ResponseDog> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseDog[]> responseEntity = restTemplate.exchange(
                backendUrl + dogUrl,
                GET,
                new HttpEntity<>(authHeaderProvider.getAuthHeader()),
                ResponseDog[].class);
        return asList(requireNonNull(responseEntity.getBody()));
    }

    @Override
    public ResponseDog create(RequestDog dog) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                backendUrl + dogUrl,
                POST,
                new HttpEntity<>(dog, authHeaderProvider.getAuthHeader()),
                ResponseDog.class).getBody();
    }

    @Override
    public void delete(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                backendUrl + dogUrl + id,
                DELETE,
                new HttpEntity<>(authHeaderProvider.getAuthHeader()),
                ResponseDog.class);
    }
}
