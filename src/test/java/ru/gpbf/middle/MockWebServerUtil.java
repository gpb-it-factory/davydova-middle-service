package ru.gpbf.middle;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class MockWebServerUtil {
    public static void runRegisterUserWithBody400(MockWebServer mockWebServer) {
        mockWebServer.enqueue(
                new MockResponse().setResponseCode(400)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(JsonData.getUnknownBackServerErrorResponse())
        );
    }

    public static void runRegisterUserWithBody409(MockWebServer mockWebServer)  {
        mockWebServer.enqueue(
                new MockResponse().setResponseCode(409)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(JsonData.getCreateUserConflictBackResponse())
        );
    }

    public static void runCreateAccountWithBody400(MockWebServer mockWebServer) {
        mockWebServer.enqueue(
                new MockResponse().setResponseCode(400)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(JsonData.getUnknownBackServerErrorResponse())
        );
    }

    public static void runCreateAccountWithBody409(MockWebServer mockWebServer) {
        mockWebServer.enqueue(
                new MockResponse().setResponseCode(409)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(JsonData.getCreateAccountConflictBackResponse())
        );
    }


    public static void runEmptyBody204(MockWebServer mockWebServer) {
        mockWebServer.enqueue(
                new MockResponse().setResponseCode(204)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        );
    }
}
