package com.gerritforge.logback.nats;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class NatsTopicAppender extends AppenderBase<ILoggingEvent> {
    private static final Logger log = LoggerFactory.getLogger(NatsClientImpl.class);

    private Optional<NatsClient> nats = Optional.empty();
    private Optional<String> topic = Optional.empty();

    private NatsClientFactory natsFactory = new NatsClientFactoryImpl();

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        nats.ifPresent(c ->
                topic.ifPresent(t ->
                        c.send(t, iLoggingEvent.getFormattedMessage().getBytes())));
    }

    public String getTopic() {
        return topic.orElse("");
    }

    public void setTopic(String topic) {
        this.topic = Optional.of(topic);
    }

    public String getUrl() {
        return nats.map(n -> n.getUrl()).orElse("");
    }

    public void setUrl(String url) {
        nats = natsFactory.create(url);
    }

    public NatsClientFactory getNatsFactoryClass() {
        return natsFactory;
    }

    public void setNatsFactory(NatsClientFactory natsFactory) {
        this.natsFactory = natsFactory;
    }
}
