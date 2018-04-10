# logback-nats-appender

[![Build Status](https://travis-ci.org/GerritForge/logback-nats-appender.svg?branch=master)](https://travis-ci.org/GerritForge/logback-nats-appender)

[LOGBack](https://logback.qos.ch/) appender for [NATS](https://nats.io/) topics

## How to build

Requires [Maven 3.x](https://maven.apache.org/) and [JDK 8](http://www.oracle.com/technetwork/java/javase/overview/index.html).

```
$ mvn install
```

## How to use

Add a LOGBack appender and configure the topic where to push the log
events.

```xml
<configuration>
    <appender name="nats" class="com.gerritforge.logback.nats.NatsTopicAppender">
        <url>nats://nats.mycompany.com:4222</url>
        <topic>mylogs</topic>
    </appender>

    <root level="INFO">
        <appender-ref ref="nats"/>
    </root>
</configuration>
```



