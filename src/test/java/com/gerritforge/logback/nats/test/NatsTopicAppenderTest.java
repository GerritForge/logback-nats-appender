package com.gerritforge.logback.nats.test;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.gerritforge.logback.nats.NatsClientFactory;
import com.gerritforge.logback.nats.NatsTopicAppender;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NatsTopicAppenderTest {

    @Test
    void shouldSubmitFormattedMessageOnEvent() {
        InMemoryNatsClient testClient = new InMemoryNatsClient();
        NatsClientFactory testClientFactory = mock(NatsClientFactory.class);
        when(testClientFactory.create(anyString())).thenReturn(Optional.of(testClient));

        NatsTopicAppender appender = new NatsTopicAppender();
        appender.setNatsFactory(testClientFactory);
        appender.setUrl("http://foo");
        appender.setTopic("bar");
        appender.start();

        ILoggingEvent mockEvent = mock(ILoggingEvent.class);
        when(mockEvent.getFormattedMessage()).thenReturn("test message");

        appender.doAppend(mockEvent);

        assertEquals("test message", new String(testClient.lastMsg));
    }

}
