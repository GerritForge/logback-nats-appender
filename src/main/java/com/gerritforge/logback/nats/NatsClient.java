package com.gerritforge.logback.nats;

import io.nats.client.*;

import java.io.IOException;

public class NatsClient {

    public void send(String topic, byte[] msg) throws IOException {
        try (Connection nc = Nats.connect()) {
            nc.publish(topic, msg);
        }
    }
}
