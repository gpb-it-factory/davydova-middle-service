package ru.gpbf.middle.web.client;

import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import ru.gpbf.middle.AbstractMockWebServerTest;
import ru.gpbf.middle.AccountData;
import ru.gpbf.middle.MockWebServerUtil;
import ru.gpbf.middle.exception.ABSServerException;
import ru.gpbf.middle.exception.ConflictServerException;
import ru.gpbf.middle.web.client.properties.ABSProperties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AccountWebClientTest extends AbstractMockWebServerTest {
    @Autowired
    private ABSProperties absProperties;

    @Autowired
    private RestTemplate restTemplate;

    private AccountWebClient accountWebClient;

    @BeforeEach
    public void setUpWebClient() {
        accountWebClient = new AccountWebClient(restTemplate, absProperties);
    }

    @Test
    void checkSuccessRequest() {
        MockWebServerUtil.runEmptyBody204(mockWebServer);

        accountWebClient.register(AccountData.ACCOUNT_REGISTER);

        RecordedRequest request = null;
        try {
            request = mockWebServer.takeRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(request.getBody().readUtf8()).isEqualTo("{\"accountName\":\"Акционный\"}");
        assertThat(request.getMethod()).isEqualTo("POST");
        assertThat(request.getPath()).isEqualTo("/v2/users/1/accounts");
    }

    @Test
    void checkSuccessRegister() {
        MockWebServerUtil.runEmptyBody204(mockWebServer);

        Assertions.assertDoesNotThrow(() -> accountWebClient.register(AccountData.ACCOUNT_REGISTER));
    }

    @Test
    void checkUnsuccessfulRegisterConflict() {
        MockWebServerUtil.runCreateAccountWithBody409(mockWebServer);

        Assertions.assertThrows(ConflictServerException.class, () -> accountWebClient.register(AccountData.ACCOUNT_REGISTER));
    }

    @Test
    void checkUnsuccessfulRegisterUnknownException() {
        MockWebServerUtil.runCreateAccountWithBody400(mockWebServer);

        Assertions.assertThrows(ABSServerException.class, () -> accountWebClient.register(AccountData.ACCOUNT_REGISTER));
    }
}