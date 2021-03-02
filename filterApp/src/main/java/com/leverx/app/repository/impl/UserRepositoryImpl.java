package com.leverx.app.repository.impl;

import com.leverx.app.entity.user.RequestUser;
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
        HttpEntity<Object> httpEntity = new HttpEntity<>(authHeaderProvider.getAuthHeader());
        ResponseEntity<User[]> responseEntity = restTemplate.exchange(
                backendUrl + userUrl,
                GET,
                httpEntity,
                User[].class);
        return asList(requireNonNull(responseEntity.getBody()));
    }

    @Override
    public User create(RequestUser user) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RequestUser> httpEntity = new HttpEntity<>(user, authHeaderProvider.getAuthHeader());
        return restTemplate.exchange(
                backendUrl + userUrl,
                POST,
                httpEntity,
                User.class).getBody();
    }

    @Override
    public void delete(long id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(authHeaderProvider.getAuthHeader());
        restTemplate.exchange(
                backendUrl + userUrl + id,
                DELETE,
                httpEntity,
                User.class);
    }
}
