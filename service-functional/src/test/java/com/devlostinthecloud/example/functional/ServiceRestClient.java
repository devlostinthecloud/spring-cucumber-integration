package com.devlostinthecloud.example.functional;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class ServiceRestClient {

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplateBuilder().build();

    public ResponseWrapper get(String relativePath) {
        try {
            RequestEntity<Void> getRequest = RequestEntity.get(URI.create("http://localhost:" + port + relativePath)).build();
            return new ResponseWrapper(restTemplate.exchange(getRequest, String.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
