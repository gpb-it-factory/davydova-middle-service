package ru.gpbf.middle.web.client;

import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import ru.gpbf.middle.AbstractMockWebServerTest;
import ru.gpbf.middle.AccountData;
import ru.gpbf.middle.MockWebServerUtil;
import ru.gpbf.middle.entity.ErrorEntity;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AccountWebClientTest extends AbstractMockWebServerTest {
    @Autowired
    private Environment environment;

    @Autowired
    private RestTemplate restTemplate;

    private AccountWebClient accountWebClient;

    @BeforeEach
    public void setUpWebClient() {
        accountWebClient = new AccountWebClient(restTemplate, environment);
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
        Optional<ErrorEntity> result = accountWebClient.register(AccountData.ACCOUNT_REGISTER);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void checkUnsuccessfulRegister() {
        MockWebServerUtil.runCreateAccountWithBody400(mockWebServer);
        Assertions.assertTrue(accountWebClient.register(AccountData.ACCOUNT_REGISTER).isPresent());

    }
}