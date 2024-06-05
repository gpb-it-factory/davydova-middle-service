package ru.gpbf.middle.web.client;

import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import ru.gpbf.middle.AbstractMockWebServerTest;
import ru.gpbf.middle.MockWebServerUtil;
import ru.gpbf.middle.UserData;
import ru.gpbf.middle.entity.ErrorEntity;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class UserWebClientTest extends AbstractMockWebServerTest {
    @Autowired
    private Environment environment;

    @Autowired
    private RestTemplate restTemplate;

    private UserWebClient userWebClient;

    @BeforeEach
    public void setUpWebClient() {
        userWebClient = new UserWebClient(restTemplate, environment);
    }

    @Test
    void checkSuccessRequest() {
        MockWebServerUtil.runEmptyBody204(mockWebServer);
        userWebClient.register(UserData.userId);
        RecordedRequest request = null;
        try {
            request = mockWebServer.takeRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(request.getBody().readUtf8()).isEqualTo("{\"userId\":1}");
        assertThat(request.getMethod()).isEqualTo("POST");
        assertThat(request.getPath()).isEqualTo("/users");
    }

    @Test
    void checkSuccessRegister() {
        MockWebServerUtil.runEmptyBody204(mockWebServer);
        Optional<ErrorEntity> result = userWebClient.register(UserData.userId);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void checkUnsuccessfulRegister() {
        MockWebServerUtil.runWithBody400(mockWebServer);
        Assertions.assertTrue(userWebClient.register(UserData.userId).isPresent());

    }

    //шансов что null туда попадет сейчас нет, проверка на всякий случай
    @Test
    void checkNotThrowExceptionIfNull() {
        MockWebServerUtil.runEmptyBody204(mockWebServer);
        userWebClient.register(null);
        RecordedRequest request = null;
        try {
            request = mockWebServer.takeRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(request.getBody().readUtf8()).isEqualTo("{\"userId\":null}");
        assertThat(request.getMethod()).isEqualTo("POST");
        assertThat(request.getPath()).isEqualTo("/users");
    }


}