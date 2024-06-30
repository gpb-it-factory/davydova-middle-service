package ru.gpbf.middle.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.gpbf.middle.APIData;
import ru.gpbf.middle.JsonData;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gpbf.middle.MockWebServerUtil.*;

class UserControllerTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBalanceSuccess() throws Exception {
        runAccountsBody200(mockWebServer);

        this.mockMvc.perform(get(APIData.GET_ACCOUNTS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"balance\":5000.00}"));
    }

    @Test
    void getBalanceNotFound() throws Exception {
        runWithEmptyList200(mockWebServer);

        this.mockMvc.perform(get(APIData.GET_ACCOUNTS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().json(JsonData.getAccountNotFoundResponse(), false));
    }

    @Test
    void getBalanceAccountDoesNotExist() throws Exception {
        runWithEmptyList200(mockWebServer);

        this.mockMvc.perform(get(APIData.GET_ACCOUNTS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().json(JsonData.getAccountNotFoundResponse(), false));
    }

    @Test
    void getBalanceUnsuccessfulConflict() throws Exception {
        runWithBody409(mockWebServer);

        this.mockMvc.perform(get(APIData.GET_ACCOUNTS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().json(JsonData.getAccountsConflictResponse()));
    }

    @Test
    void getBalanceUnsuccessfulUnknownServerException() throws Exception {
        runWithBody400(mockWebServer);

        this.mockMvc.perform(get(APIData.GET_ACCOUNTS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().json(JsonData.getUnknownErrorResponse()));
    }

}