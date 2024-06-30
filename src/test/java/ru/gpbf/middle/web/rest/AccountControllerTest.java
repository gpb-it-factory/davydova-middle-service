package ru.gpbf.middle.web.rest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.gpbf.middle.APIData;
import ru.gpbf.middle.JsonData;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gpbf.middle.MockWebServerUtil.*;


class AccountControllerTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerSuccess() throws Exception {
        runEmptyBody204(mockWebServer);

        this.mockMvc.perform(post(APIData.CREATE_ACCOUNT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonData.CREATE_ACCOUNT_REQUEST)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(Matchers.blankString()));

    }

    @Test
    void registerUnsuccessfulConflict() throws Exception {
        runWithBody409(mockWebServer);

        this.mockMvc.perform(post(APIData.CREATE_ACCOUNT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonData.CREATE_ACCOUNT_REQUEST)).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().json(JsonData.getCreateAccountConflictResponse()));
    }

    @Test
    void registerUnsuccessfulUnknownServerException() throws Exception {
        runWithBody400(mockWebServer);

        this.mockMvc.perform(post(APIData.CREATE_ACCOUNT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonData.CREATE_ACCOUNT_REQUEST)).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().json(JsonData.getUnknownErrorResponse()));
    }

    @Test
    void registerBadAccountName() throws Exception {
        this.mockMvc.perform(post(APIData.CREATE_ACCOUNT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonData.CREATE_ACCOUNT_REQUEST_BAD_ACCOUNT_NAME)).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.notNullValue()));

    }

    @Test
    void registerUnsuccessfulBadJson() throws Exception {
        this.mockMvc.perform(post(APIData.CREATE_ACCOUNT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userTelegramId\":null}")).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.notNullValue()));

    }

    @Test
    void registerUnsuccessfulNullJson() throws Exception {
        this.mockMvc.perform(post(APIData.CREATE_ACCOUNT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.notNullValue()));
    }

}