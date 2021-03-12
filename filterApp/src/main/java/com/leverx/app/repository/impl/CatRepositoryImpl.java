package com.leverx.app.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.app.entity.request.cat.RequestCat;
import com.leverx.app.entity.response.cat.ResponseCat;
import com.leverx.app.exceptions.RepositoryException;
import com.leverx.app.provider.DestinationProvider;
import com.leverx.app.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

@RequiredArgsConstructor
@Component
@Log4j2
public class CatRepositoryImpl implements CatRepository {

    @Value(value = "${cat.url}")
    private final String catUrl;
    private final ObjectMapper mapper;
    private final DestinationProvider destinationProvider;

    @Override
    public List<ResponseCat> findAll() {
        try {
            String responseStr = destinationProvider.get(catUrl);
            return asList(mapper.readValue(responseStr, ResponseCat[].class));
        } catch (IOException e) {
            throw new RepositoryException("can't get cats");
        }
    }

    @Override
    public ResponseCat create(RequestCat cat) {
        try {
            String responseStr = destinationProvider
                    .post(catUrl, mapper.writeValueAsString(cat));
            return mapper.readValue(responseStr, ResponseCat.class);
        } catch (IOException e) {
            throw new RepositoryException("can't create cat");
        }
    }

    @Override
    public void delete(long id) {
        try {
            destinationProvider.delete(catUrl, id);
        } catch (IOException e) {
            throw new RepositoryException("can't delete cat");
        }
    }
}
