package ru.gpbf.middle.application.gateway;

import org.springframework.stereotype.Service;
import ru.gpbf.middle.domain.User;
import ru.gpbf.middle.web.client.UserWebClient;

@Service
public class UserGatewayImpl implements UserGateway {
    private final UserWebClient userWebClient;

    public UserGatewayImpl(UserWebClient userWebClient) {
        this.userWebClient = userWebClient;
    }

    @Override
    public void register(User user) {
        userWebClient.register(user);
    }
}
