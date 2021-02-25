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
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpMethod.GET;

@RequiredArgsConstructor
@Component
public class UserRepositoryImpl implements UserRepository {

    @Value(value = "${backend.server.url}")
    private String backendUrl;
    @Value(value = "${user.url}")
    private String userUrl;
    private final AuthProvider authProvider;

    public List<User> findAll() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<User[]> responseEntity = restTemplate
                    .exchange(backendUrl + userUrl, GET, entityWithHeaders(), User[].class);
            return asList(requireNonNull(responseEntity.getBody()));
        } catch (HttpStatusCodeException e) {
            return emptyList();
        }
    }

    private HttpEntity<User> entityWithHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String auth = authProvider.getAuth();
        headers.set("Authorization", auth);
        return new HttpEntity<>(headers);
    }
}
