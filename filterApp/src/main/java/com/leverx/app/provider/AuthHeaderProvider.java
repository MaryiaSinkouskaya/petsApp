package com.leverx.app.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class AuthHeaderProvider {

    private final AuthProvider authProvider;

    public HttpHeaders getAuthHeader() {
        HttpHeaders headers = new HttpHeaders();
        String auth = authProvider.getAuth();
        headers.set("Authorization", auth);
        return headers;
    }

}
