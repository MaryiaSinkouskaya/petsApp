package com.leverx.app.repository.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.app.entity.response.pet.ResponsePet;
import com.leverx.app.exceptions.RepositoryException;
import com.leverx.app.provider.DestinationProvider;
import com.leverx.app.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

@RequiredArgsConstructor
@Component
public class PetRepositoryImpl implements PetRepository {

    @Value(value = "${pet.url}")
    private final String petUrl;
    private final ObjectMapper mapper;
    private final DestinationProvider destinationProvider;

    @Override
    public List<ResponsePet> findAll() {
        try {
            String responseStr = destinationProvider.get(petUrl);
            return asList(mapper.readValue(responseStr, ResponsePet[].class));
        } catch (IOException e) {
            throw new RepositoryException("can't get pets");
        }
    }
}
