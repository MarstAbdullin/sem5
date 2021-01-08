package ru.javalab.consumers.fanout;

import ru.javalab.consumers.Consumer;
import ru.javalab.data.Keys;

public class FanoutCounterConsumer extends Consumer {

    private int counter = 0;

    @Override
    protected String getFilename() {
        return "requestCounter.pdf";
    }

    @Override
    protected String exchangeName() {
        return Keys.EXCHANGE_NAME_FANOUT;
    }

    @Override
    protected String exchangeType() {
        return Keys.EXCHANGE_FANOUT;
    }

    @Override
    protected String exchangeRoutingKey() {
        return "";
    }

    @Override
    protected String queueName() {
        return Keys.EXCHANGE_NAME_FANOUT + "_queue";
    }

    @Override
    protected String templateName() {
        return "fanout.html";
    }

    @Override
    protected String data(String message) {
        return String.valueOf(++counter);
    }
}
