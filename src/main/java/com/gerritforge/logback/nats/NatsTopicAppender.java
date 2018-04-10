package com.gerritforge.logback.nats;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.io.IOException;

public class NatsTopicAppender extends AppenderBase<ILoggingEvent> {
    private NatsClient nats = new NatsClient();

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        try {
            nats.send("foo", iLoggingEvent.getFormattedMessage().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
