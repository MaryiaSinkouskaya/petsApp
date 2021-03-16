package com.leverx.app.repository.impl.destination;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.app.entity.request.dog.RequestDog;
import com.leverx.app.entity.response.dog.ResponseDog;
import com.leverx.app.exceptions.RepositoryException;
import com.leverx.app.provider.DestinationProvider;
import com.leverx.app.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

@RequiredArgsConstructor
@Component
@Profile("destination")
public class DogRepositoryImpl implements DogRepository {

    @Value(value = "${dog.url}")
    private final String dogUrl;
    private final ObjectMapper mapper;
    private final DestinationProvider destinationProvider;

    @Override
    public List<ResponseDog> findAll() {
        try {
            String responseStr = destinationProvider.get(dogUrl);
            return asList(mapper.readValue(responseStr, ResponseDog[].class));
        } catch (IOException e) {
            throw new RepositoryException("can't get dogs");
        }
    }

    @Override
    public ResponseDog create(RequestDog dog) {
        try {
            String responseStr = destinationProvider
                    .post(dogUrl, mapper.writeValueAsString(dog));
            return mapper.readValue(responseStr, ResponseDog.class);
        } catch (IOException e) {
            throw new RepositoryException("can't create dog");
        }
    }

    @Override
    public void delete(long id) {
        try {
            destinationProvider.delete(dogUrl, id);
        } catch (IOException e) {
            throw new RepositoryException("can't delete dog");
        }
    }
}
