package ru.gpbf.middle.web.client;

import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import ru.gpbf.middle.AbstractMockWebServerTest;
import ru.gpbf.middle.MockWebServerUtil;
import ru.gpbf.middle.UserData;
import ru.gpbf.middle.domain.User;
import ru.gpbf.middle.exception.ABSServerException;
import ru.gpbf.middle.exception.ConflictServerException;
import ru.gpbf.middle.web.client.properties.ABSProperties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class UserWebClientTest extends AbstractMockWebServerTest {
    @Autowired
    private ABSProperties absProperties;

    @Autowired
    private RestTemplate restTemplate;

    private UserWebClient userWebClient;

    @BeforeEach
    public void setUpWebClient() {
        userWebClient = new UserWebClient(restTemplate, absProperties);
    }

    @Test
    void checkSuccessRequest() {
        MockWebServerUtil.runEmptyBody204(mockWebServer);
        userWebClient.register(UserData.USER);
        RecordedRequest request = null;
        try {
            request = mockWebServer.takeRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(request.getBody().readUtf8()).isEqualTo("{\"userId\":1,\"userName\":\"alina\"}");
        assertThat(request.getMethod()).isEqualTo("POST");
        assertThat(request.getPath()).isEqualTo("/v2/users");
    }

    @Test
    void checkSuccessRegister() {
        MockWebServerUtil.runEmptyBody204(mockWebServer);

        Assertions.assertDoesNotThrow(() -> userWebClient.register(UserData.USER));
    }

    @Test
    void checkUnsuccessfulRegisterConflict() {
        MockWebServerUtil.runRegisterUserWithBody409(mockWebServer);

        Assertions.assertThrows(ConflictServerException.class, () -> userWebClient.register(UserData.USER));

    }

    @Test
    void checkUnsuccessfulRegisterUnknownException() {
        MockWebServerUtil.runRegisterUserWithBody400(mockWebServer);

        Assertions.assertThrows(ABSServerException.class, () -> userWebClient.register(UserData.USER));

    }

    //шансов что null туда попадет сейчас нет, проверка на всякий случай
    @Test
    void checkNotThrowExceptionIfNull() {
        MockWebServerUtil.runEmptyBody204(mockWebServer);
        userWebClient.register(new User(null, null));
        RecordedRequest request = null;
        try {
            request = mockWebServer.takeRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(request.getBody().readUtf8()).isEqualTo("{\"userId\":null,\"userName\":null}");
        assertThat(request.getMethod()).isEqualTo("POST");
        assertThat(request.getPath()).isEqualTo("/v2/users");
    }


}