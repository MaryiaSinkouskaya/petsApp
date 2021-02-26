package com.leverx.app.repository.impl;


import com.leverx.app.entity.pet.Pet;
import com.leverx.app.provider.AuthProvider;
import com.leverx.app.repository.PetRepository;
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

@RequiredArgsConstructor
@Component
public class PetRepositoryImpl implements PetRepository {

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    @Value(value = "${pet.url}")
    private final String petUrl;
    private final AuthProvider authProvider;

    public List<Pet> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String auth = authProvider.getAuth();
        headers.set("Authorization", auth);

        ResponseEntity<Pet[]> responseEntity = restTemplate
                .exchange(backendUrl + petUrl, GET, new HttpEntity<>(headers), Pet[].class);
            return asList(requireNonNull(responseEntity.getBody()));
    }

}
