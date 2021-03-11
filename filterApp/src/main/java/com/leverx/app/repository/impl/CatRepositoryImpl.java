package com.leverx.app.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.app.entity.request.cat.RequestCat;
import com.leverx.app.entity.response.cat.ResponseCat;
import com.leverx.app.exceptions.RepositoryException;
import com.leverx.app.repository.CatRepository;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class CatRepositoryImpl implements CatRepository {

    @Value(value = "${cat.url}")
    private final String catUrl;
    private final ObjectMapper mapper;
    private final HttpDestination httpDestination = DestinationAccessor
            .getDestination("petsDestination")
            .asHttp();
    private final HttpClient httpClient = HttpClientAccessor.getHttpClient(httpDestination);

    public List<ResponseCat> findAll() {
        try {
            HttpGet httpGet = new HttpGet(httpDestination.getUri() + catUrl);
            HttpResponse response = httpClient.execute(httpGet);
            String responseStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            return asList(mapper.readValue(responseStr, ResponseCat[].class));
        } catch (IOException e) {
            throw new RepositoryException("can't get cats");
        }
    }

    @Override
    public ResponseCat create(RequestCat cat) {
        try {
            HttpPost httpPost = new HttpPost(httpDestination.getUri() + catUrl);
            httpPost.setEntity(new StringEntity(mapper.writeValueAsString(cat)));
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse response = httpClient.execute(httpPost);
            String responseStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            return mapper.readValue(responseStr, ResponseCat.class);
        } catch (IOException e) {
            throw new RepositoryException("can't create cat");
        }
    }

    @Override
    public void delete(long id) {
        try {
            HttpDelete httpDelete = new HttpDelete(httpDestination.getUri() + catUrl + id);
            httpClient.execute(httpDelete);
        } catch (IOException e) {
            throw new RepositoryException("can't delete cat");
        }
    }
}
