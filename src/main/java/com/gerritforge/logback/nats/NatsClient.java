package com.gerritforge.logback.nats;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public interface NatsClient {

    String getUrl();

    void send(String topic, byte[] msg);
}
