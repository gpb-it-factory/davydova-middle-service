package ru.gpbf.middle.config;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class WebClientErrorHandler implements ResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) {
        // do not throw exception on 4xx/5xx, just to return response entity for manual introspection
    }


    @Override
    public boolean hasError(ClientHttpResponse response) {
        return false;
    }
}

