package com.leverx.app.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.app.entity.request.user.RequestUser;
import com.leverx.app.entity.response.user.ResponseUser;
import com.leverx.app.exceptions.RepositoryException;
import com.leverx.app.repository.UserRepository;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

@RequiredArgsConstructor
@Component
public class UserRepositoryImpl implements UserRepository {

    @Value(value = "${user.url}")
    private final String userUrl;
    private final ObjectMapper mapper;
    private final HttpDestination httpDestination = DestinationAccessor
            .getDestination("petsDestination")
            .asHttp();
    private final HttpClient httpClient = HttpClientAccessor.getHttpClient(httpDestination);

    public List<ResponseUser> findAll() {
        try {
            HttpGet httpGet = new HttpGet(httpDestination.getUri() + userUrl);
            HttpResponse response = httpClient.execute(httpGet);
            String responseStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            return asList(mapper.readValue(responseStr, ResponseUser[].class));
        } catch (IOException e) {
            throw new RepositoryException("can't get users");
        }
    }

    @Override
    public ResponseUser create(RequestUser user) {
        try {
            HttpPost httpPost = new HttpPost(httpDestination.getUri() + userUrl);
            httpPost.setEntity(new StringEntity(mapper.writeValueAsString(user)));
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse response = httpClient.execute(httpPost);
            String responseStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            return mapper.readValue(responseStr, ResponseUser.class);
        } catch (IOException e) {
            throw new RepositoryException("can't create user");
        }
    }

    @Override
    public void delete(long id) {
        try {
            HttpDelete httpDelete = new HttpDelete(httpDestination.getUri() + userUrl + id);
            httpClient.execute(httpDelete);
        } catch (IOException e) {
            throw new RepositoryException("can't delete user");
        }
    }
}
