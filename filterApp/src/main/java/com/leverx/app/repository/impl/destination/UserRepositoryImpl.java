package com.leverx.app.repository.impl.destination;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.app.entity.request.user.RequestUser;
import com.leverx.app.entity.response.user.ResponseUser;
import com.leverx.app.exceptions.RepositoryException;
import com.leverx.app.provider.DestinationProvider;
import com.leverx.app.repository.UserRepository;
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
public class UserRepositoryImpl implements UserRepository {

    @Value(value = "${user.url}")
    private final String userUrl;
    private final ObjectMapper mapper;
    private final DestinationProvider destinationProvider;

    @Override
    public List<ResponseUser> findAll() {
        try {
            String responseStr = destinationProvider.get(userUrl);
            return asList(mapper.readValue(responseStr, ResponseUser[].class));
        } catch (IOException e) {
            throw new RepositoryException("can't get users");
        }
    }

    @Override
    public ResponseUser create(RequestUser user) {
        try {
            String responseStr = destinationProvider
                    .post(userUrl, mapper.writeValueAsString(user));
            return mapper.readValue(responseStr, ResponseUser.class);
        } catch (IOException e) {
            throw new RepositoryException("can't create user");
        }
    }

    @Override
    public void delete(long id) {
        try {
            destinationProvider.delete(userUrl, id);
        } catch (IOException e) {
            throw new RepositoryException("can't delete user");
        }
    }
}
