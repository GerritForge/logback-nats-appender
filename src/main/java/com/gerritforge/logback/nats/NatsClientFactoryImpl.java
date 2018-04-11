package com.gerritforge.logback.nats;

import java.util.Optional;

public class NatsClientFactoryImpl implements NatsClientFactory {

    @Override
    public Optional<NatsClient> create(String url) {
        return Optional.of(new NatsClientImpl(url));
    }
}
