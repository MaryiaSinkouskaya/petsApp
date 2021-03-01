package com.leverx.app.repository.impl;

import com.leverx.app.entity.user.User;
import com.leverx.app.provider.AuthHeaderProvider;
import com.leverx.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
public class UserRepositoryImpl implements UserRepository {

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    @Value(value = "${user.url}")
    private final String userUrl;
    private final AuthHeaderProvider authHeaderProvider;

    public List<User> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> responseEntity = restTemplate.exchange(
                backendUrl + userUrl,
                GET,
                new HttpEntity<>(authHeaderProvider.getAuthHeader()),
                User[].class);
        return asList(requireNonNull(responseEntity.getBody()));
    }

    @Override
    public User create(User user) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                backendUrl + userUrl,
                POST,
                new HttpEntity<>(user, authHeaderProvider.getAuthHeader()),
                User.class).getBody();
    }

    @Override
    public void delete(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                backendUrl + userUrl + id,
                DELETE,
                new HttpEntity<>(authHeaderProvider.getAuthHeader()),
                User.class);
    }
}
