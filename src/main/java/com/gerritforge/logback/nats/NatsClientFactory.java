package com.gerritforge.logback.nats;

import java.util.Optional;

public interface NatsClientFactory {

    public Optional<NatsClient> create(String url);
}
