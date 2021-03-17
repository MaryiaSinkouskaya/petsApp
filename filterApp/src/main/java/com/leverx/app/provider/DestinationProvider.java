package com.leverx.app.provider;

import com.sap.cloud.sdk.cloudplatform.connectivity.Destination;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

import static com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor.*;
import static com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class DestinationProvider {

    private final Destination destination = getDestination("petsDestination");
    private final HttpClient httpClient = getHttpClient(destination.asHttp());
    private final URI destinationUri = destination.asHttp().getUri();

    public String get(String uri) throws IOException {
        HttpGet httpGet = new HttpGet(destinationUri + uri);
        HttpResponse response = httpClient.execute(httpGet);
        return EntityUtils.toString(response.getEntity(), UTF_8.name());
    }

    public String post(String uri, String entity) throws IOException {
        HttpPost httpPost = new HttpPost(destinationUri + uri);
        httpPost.setEntity(new StringEntity(entity));
        httpPost.setHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        HttpResponse response = httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), UTF_8.name());
    }

    public void delete(String uri, long id) throws IOException {
        HttpDelete httpDelete = new HttpDelete(destinationUri + uri + id);
        httpClient.execute(httpDelete);
    }

}
