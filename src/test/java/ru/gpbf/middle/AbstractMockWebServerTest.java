package ru.gpbf.middle;

import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

@SpringBootTest
@ActiveProfiles("test")
public abstract class AbstractMockWebServerTest {
    public MockWebServer mockWebServer;

    @BeforeEach
    void setupMockWebServer() {
        mockWebServer = new MockWebServer();
        try {
            mockWebServer.start(Integer.parseInt("1111"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @AfterEach
    void shutDownMockWebServer() {
        try {
            mockWebServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
