package ru.gpbf.middle;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class MockWebServerUtil {
    public static void runWithBody400(MockWebServer mockWebServer) {
        mockWebServer.enqueue(
                new MockResponse().setResponseCode(400)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(WebClientData.USERS_REGISTER)
        );
    }

    public static void runCreateAccountWithBody400(MockWebServer mockWebServer) {
        mockWebServer.enqueue(
                new MockResponse().setResponseCode(400)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(WebClientData.ACCOUNT_REGISTER)
        );
    }


    public static void runEmptyBody204(MockWebServer mockWebServer) {
        mockWebServer.enqueue(
                new MockResponse().setResponseCode(204)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        );
    }
}
