package com.leverx.app.repository.impl.template;


import com.leverx.app.entity.response.pet.ResponsePet;
import com.leverx.app.provider.authentification.AuthHeaderProvider;
import com.leverx.app.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
@Component
@Profile("template")
public class PetRepositoryImpl implements PetRepository {

    @Value(value = "${backend.cloud.server.url}")
    private final String backendUrl;
    @Value(value = "${pet.url}")
    private final String petUrl;
    private final AuthHeaderProvider authHeaderProvider;

    public List<ResponsePet> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponsePet[]> responseEntity = restTemplate.exchange(
                backendUrl + petUrl,
                HttpMethod.GET,
                new HttpEntity<>(authHeaderProvider.getAuthHeader()),
                ResponsePet[].class);
        return asList(requireNonNull(responseEntity.getBody()));
    }
}
