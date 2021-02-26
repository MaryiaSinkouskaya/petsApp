package com.leverx.app.repository.impl;

import com.leverx.app.entity.user.User;
import com.leverx.app.provider.AuthProvider;
import com.leverx.app.repository.UserRepository;
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
import static org.springframework.http.HttpMethod.POST;

@RequiredArgsConstructor
@Component
public class UserRepositoryImpl implements UserRepository {

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    @Value(value = "${user.url}")
    private final String userUrl;
    private final AuthProvider authProvider;


    public List<User> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String auth = authProvider.getAuth();
        headers.set("Authorization", auth);

        ResponseEntity<User[]> responseEntity = restTemplate
                .exchange(backendUrl + userUrl, GET, new HttpEntity<>(headers), User[].class);
            return asList(requireNonNull(responseEntity.getBody()));
    }

    @Override
    public User create(User dog) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String auth = authProvider.getAuth();
        headers.set("Authorization", auth);
        return restTemplate
                .exchange(backendUrl + userUrl, POST, new HttpEntity<>(dog, headers), User.class)
                .getBody();
    }
}
