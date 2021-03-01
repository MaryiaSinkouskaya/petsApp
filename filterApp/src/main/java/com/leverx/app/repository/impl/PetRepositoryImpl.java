package com.leverx.app.repository.impl;


import com.leverx.app.entity.pet.Pet;
import com.leverx.app.provider.AuthHeaderProvider;
import com.leverx.app.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpMethod.GET;

@RequiredArgsConstructor
@Component
public class PetRepositoryImpl implements PetRepository {

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    @Value(value = "${pet.url}")
    private final String petUrl;
    private final AuthHeaderProvider authHeaderProvider;

    public List<Pet> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Pet[]> responseEntity = restTemplate.exchange(
                backendUrl + petUrl,
                GET,
                new HttpEntity<>(authHeaderProvider.getAuthHeader()),
                Pet[].class);
        return asList(requireNonNull(responseEntity.getBody()));
    }
}
