package com.gerritforge.logback.nats;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class NatsClientImpl implements NatsClient {
    private static final Logger log = LoggerFactory.getLogger(NatsClientImpl.class);

    private final String url;

    public NatsClientImpl(String url) {
        this.url = url;
    }

    @Override
    public void send(String topic, byte[] msg) {
        try (Connection nc = Nats.connect(url)) {
            nc.publish(topic, msg);
        } catch (IOException e) {
            log.error("Unable to push logging event to {}/{}", url, topic, e);
        }
    }

    @Override
    public String getUrl() {
        return url;
    }
}
