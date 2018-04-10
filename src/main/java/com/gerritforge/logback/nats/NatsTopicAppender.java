package com.gerritforge.logback.nats;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class NatsTopicAppender extends AppenderBase<ILoggingEvent> {
    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        System.out.println("NATS: " + iLoggingEvent.getFormattedMessage());
    }
}
