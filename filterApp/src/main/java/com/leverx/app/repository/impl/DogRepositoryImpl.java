package com.leverx.app.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.app.entity.request.dog.RequestDog;
import com.leverx.app.entity.response.dog.ResponseDog;
import com.leverx.app.exceptions.RepositoryException;
import com.leverx.app.repository.DogRepository;
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
public class DogRepositoryImpl implements DogRepository {

    @Value(value = "${dog.url}")
    private final String dogUrl;
    private final ObjectMapper mapper;
    private final HttpDestination httpDestination = DestinationAccessor
            .getDestination("petsDestination")
            .asHttp();
    private final HttpClient httpClient = HttpClientAccessor.getHttpClient(httpDestination);


    public List<ResponseDog> findAll() {
        try {
            HttpGet httpGet = new HttpGet(httpDestination.getUri() + dogUrl);
            HttpResponse response = httpClient.execute(httpGet);
            String responseStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            return asList(mapper.readValue(responseStr, ResponseDog[].class));
        } catch (IOException e) {
            throw new RepositoryException("can't get dogs");
        }
    }

    @Override
    public ResponseDog create(RequestDog dog) {
        try {
            HttpPost httpPost = new HttpPost(httpDestination.getUri() + dogUrl);
            httpPost.setEntity(new StringEntity(mapper.writeValueAsString(dog)));
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse response = httpClient.execute(httpPost);
            String responseStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            return mapper.readValue(responseStr, ResponseDog.class);
        } catch (IOException e) {
            throw new RepositoryException("can't create dog");
        }
    }

    @Override
    public void delete(long id) {
        try {
            HttpDelete httpDelete = new HttpDelete(httpDestination.getUri() + dogUrl + id);
            httpClient.execute(httpDelete);
        } catch (IOException e) {
            throw new RepositoryException("can't delete dog");
        }
    }
}
