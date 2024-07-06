package ru.gpbf.middle.web.client;

import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import ru.gpbf.middle.*;
import ru.gpbf.middle.entity.AccountEntity;
import ru.gpbf.middle.exception.ABSServerException;
import ru.gpbf.middle.exception.ConflictServerException;
import ru.gpbf.middle.web.client.properties.ABSProperties;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AccountWebClientTest extends AbstractMockWebServerTest {
    @Autowired
    private ABSProperties absProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestClient restClient;

    private AccountWebClient accountWebClient;

    @BeforeEach
    public void setUpWebClient() {
        accountWebClient = new AccountWebClient(restClient, restTemplate, absProperties);
    }

    @Test
    void checkSuccessRegisterRequest() {
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
        MockWebServerUtil.runWithBody409(mockWebServer);

        Assertions.assertThrows(ConflictServerException.class, () -> accountWebClient.register(AccountData.ACCOUNT_REGISTER));
    }

    @Test
    void checkUnsuccessfulRegisterUnknownException() {
        MockWebServerUtil.runWithBody400(mockWebServer);

        Assertions.assertThrows(ABSServerException.class, () -> accountWebClient.register(AccountData.ACCOUNT_REGISTER));
    }

    @Test
    void checkSuccessGetAccountsRequest() {
        MockWebServerUtil.runAccountsBody200(mockWebServer);

        accountWebClient.getAccounts(UserData.USER_ID);

        RecordedRequest request = null;
        try {
            request = mockWebServer.takeRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(request.getBody().readUtf8()).isEmpty();
        assertThat(request.getMethod()).isEqualTo("GET");
        assertThat(request.getPath()).isEqualTo("/v2/users/1/accounts");
    }

    @Test
    void checkSuccessGetAccounts() {
        MockWebServerUtil.runAccountsBody200(mockWebServer);

        List<AccountEntity> accountEntities = accountWebClient.getAccounts(UserData.USER_ID);

        Matcher.match(accountEntities, AccountData.ACCOUNT_ENTITY_LIST);
    }

    @Test
    void checkUnsuccessfulGetBalanceConflict() {
        MockWebServerUtil.runWithBody409(mockWebServer);

        Assertions.assertThrows(ConflictServerException.class, () -> accountWebClient.getAccounts(UserData.USER_ID));
    }

    @Test
    void checkUnsuccessfulRegisterNotFound() {
        MockWebServerUtil.runWithEmptyList200(mockWebServer);

        List<AccountEntity> accountEntities = accountWebClient.getAccounts(UserData.USER_ID);

        Assertions.assertTrue(accountEntities.isEmpty());
    }

    @Test
    void checkUnsuccessfulGetBalanceUnknownException() {
        MockWebServerUtil.runWithBody400(mockWebServer);

        Assertions.assertThrows(ABSServerException.class, () -> accountWebClient.getAccounts(UserData.USER_ID));
    }
}