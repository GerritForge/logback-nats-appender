package com.gerritforge.logback.nats;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.Optional;

public class NatsTopicAppender extends AppenderBase<ILoggingEvent> {
    private Optional<NatsClient> nats = Optional.empty();

    private Optional<String> topic = Optional.empty();

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
        return nats.map(n -> n.url).orElse("");
    }

    public void setUrl(String url) {
        nats = Optional.of(new NatsClient(url));
    }
}
