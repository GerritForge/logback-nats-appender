package com.gerritforge.logback.nats;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class NatsClientImpl implements NatsClient {
    private static final long ERROR_RATE_INTERVAL_MSEC = 60 * 1000L;
    private static final Logger log = LoggerFactory.getLogger(NatsClientImpl.class);

    private final String url;

    private final AtomicLong lastErrorTs = new AtomicLong();

    public NatsClientImpl(String url) {
        this.url = url;
    }

    @Override
    public void send(String topic, byte[] msg) {
        try (Connection nc = Nats.connect(url)) {
            nc.publish(topic, msg);
        } catch (IOException e) {
            long errorsTs = lastErrorTs.get();
            long currentTs = System.currentTimeMillis();

            if((errorsTs + ERROR_RATE_INTERVAL_MSEC) < currentTs) {
                log.error("Unable to push logging event to {}/{}", url, topic, e);
                lastErrorTs.set(currentTs);
            }
        }
    }

    @Override
    public String getUrl() {
        return url;
    }
}
