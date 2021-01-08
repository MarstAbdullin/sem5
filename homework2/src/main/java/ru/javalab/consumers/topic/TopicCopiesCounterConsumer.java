package ru.javalab.consumers.topic;

import ru.javalab.consumers.Consumer;
import ru.javalab.data.Keys;

public class TopicCopiesCounterConsumer extends Consumer {

    private int counter = 0;

    @Override
    protected String getFilename() {
        return "copiesCounter.pdf";
    }

    @Override
    protected String exchangeName() {
        return Keys.EXCHANGE_NAME_TOPIC;
    }

    @Override
    protected String exchangeType() {
        return Keys.EXCHANGE_TOPIC;
    }

    @Override
    protected String exchangeRoutingKey() {
        return "document.copy.*";
    }

    @Override
    protected String queueName() {
        return Keys.EXCHANGE_NAME_TOPIC + "_queue";
    }

    @Override
    protected String templateName() {
        return "topic.html";
    }

    @Override
    protected String data(String message) {
        return String.valueOf(++counter);
    }
}
