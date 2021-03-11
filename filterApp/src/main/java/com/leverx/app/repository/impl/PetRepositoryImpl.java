package com.leverx.app.repository.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.app.entity.response.pet.ResponsePet;
import com.leverx.app.exceptions.RepositoryException;
import com.leverx.app.repository.PetRepository;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
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
    private final HttpDestination httpDestination = DestinationAccessor
            .getDestination("petsDestination")
            .asHttp();
    private final HttpClient httpClient = HttpClientAccessor.getHttpClient(httpDestination);

    public List<ResponsePet> findAll() {
        try {
            HttpGet httpGet = new HttpGet(httpDestination.getUri() + petUrl);
            HttpResponse response = httpClient.execute(httpGet);
            String responseStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            return asList(mapper.readValue(responseStr, ResponsePet[].class));
        } catch (IOException e) {
            throw new RepositoryException("can't get pets");
        }
    }
}
