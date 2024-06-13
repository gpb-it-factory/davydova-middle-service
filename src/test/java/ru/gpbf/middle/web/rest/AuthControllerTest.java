package ru.gpbf.middle.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gpbf.middle.MockWebServerUtil.runEmptyBody204;
import static ru.gpbf.middle.MockWebServerUtil.runWithBody400;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.gpbf.middle.APIData;
import ru.gpbf.middle.AbstractMockWebServerTest;
import ru.gpbf.middle.JsonData;
import ru.gpbf.middle.WebClientData;


@AutoConfigureMockMvc
class AuthControllerTest extends AbstractMockWebServerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerSuccess() throws Exception {
        runEmptyBody204(mockWebServer);

        this.mockMvc.perform(post(APIData.REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonData.userRequest)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(Matchers.blankString()));

    }

    @Test
    void registerUnsuccessful() throws Exception {
        runWithBody400(mockWebServer);

        this.mockMvc.perform(post(APIData.REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonData.userRequest)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(WebClientData.USERS_REGISTER));

    }

    @Test
    void registerUnsuccessfulBadJson() throws Exception {
        this.mockMvc.perform(post(APIData.REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userTelegramId\":null}")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(Matchers.notNullValue()));

    }

    @Test
    void registerUnsuccessfulNullJson() throws Exception {
        this.mockMvc.perform(post(APIData.REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(Matchers.notNullValue()));
    }
}