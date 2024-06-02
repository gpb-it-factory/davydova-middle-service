package ru.gpbf.middle;

import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

@SpringBootTest
@ActiveProfiles("test")
public abstract class AbstractMockWebServerTest {
    public MockWebServer mockWebServer;

    @Autowired
    public Environment environment;

    @BeforeEach
    void setupMockWebServer() {
        mockWebServer = new MockWebServer();
        try {
            mockWebServer.start(Integer.parseInt(environment.getProperty("abs.port")));
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
