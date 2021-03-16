package com.leverx.app.repository.impl.template;

import com.leverx.app.entity.request.user.RequestUser;
import com.leverx.app.entity.response.user.ResponseUser;
import com.leverx.app.provider.authentification.AuthHeaderProvider;
import com.leverx.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
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
@Profile("template")
public class UserRepositoryImpl implements UserRepository {

    @Value(value = "${backend.cloud.server.url}")
    private final String backendUrl;
    @Value(value = "${user.url}")
    private final String userUrl;
    private final AuthHeaderProvider authHeaderProvider;

    public List<ResponseUser> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseUser[]> responseEntity = restTemplate.exchange(
                backendUrl + userUrl,
                GET,
                new HttpEntity<>(authHeaderProvider.getAuthHeader()),
                ResponseUser[].class);
        return asList(requireNonNull(responseEntity.getBody()));
    }

    @Override
    public ResponseUser create(RequestUser user) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                backendUrl + userUrl,
                POST,
                new HttpEntity<>(user, authHeaderProvider.getAuthHeader()),
                ResponseUser.class).getBody();
    }

    @Override
    public void delete(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                backendUrl + userUrl + id,
                DELETE,
                new HttpEntity<>(authHeaderProvider.getAuthHeader()),
                ResponseUser.class);
    }
}
