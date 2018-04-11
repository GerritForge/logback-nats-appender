package com.gerritforge.logback.nats.test;

import com.gerritforge.logback.nats.NatsClient;

public class InMemoryNatsClient implements NatsClient {
    public String lastTopic;
    public byte[] lastMsg;

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public void send(String topic, byte[] msg) {
        this.lastTopic = topic;
        this.lastMsg = msg;
    }
}
