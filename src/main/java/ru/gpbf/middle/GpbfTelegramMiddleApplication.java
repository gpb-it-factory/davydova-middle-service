package ru.gpbf.middle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.gpbf.middle.web.client.properties.ABSProperties;

@SpringBootApplication
@EnableConfigurationProperties(ABSProperties.class)
public class GpbfTelegramMiddleApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpbfTelegramMiddleApplication.class, args);
    }

}
