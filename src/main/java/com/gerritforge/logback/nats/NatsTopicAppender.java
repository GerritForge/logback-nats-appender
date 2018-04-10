package com.gerritforge.logback.nats;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.io.IOException;

public class NatsTopicAppender extends AppenderBase<ILoggingEvent> {
    private NatsClient nats = new NatsClient();

    private String topic = "foo";

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        try {
            nats.send(topic, iLoggingEvent.getFormattedMessage().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
