package com.gerritforge.logback.nats;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class NatsClient {
    private static final Logger log = LoggerFactory.getLogger(NatsClient.class);

    public final String url;

    public NatsClient(String url) {
        this.url = url;
    }

    public void send(String topic, byte[] msg) {
        try (Connection nc = Nats.connect(url)) {
            nc.publish(topic, msg);
        } catch (IOException e) {
            log.error("Unable to push logging event to {}/{}", url, topic, e);
        }
    }
}
