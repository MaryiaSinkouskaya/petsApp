package com.leverx.app.provider;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Setter
@Getter
public class AuthProvider {

    private String auth;
}
