package ru.javalab.consumers.direct;

import ru.javalab.consumers.Consumer;
import ru.javalab.data.Keys;

import java.util.UUID;

public class DirectOtgihConsumer extends Consumer {

    @Override
    protected String getFilename() {
        return "direct_otdih_" + UUID.randomUUID() + ".pdf";
    }

    @Override
    protected String exchangeName() {
        return Keys.EXCHANGE_NAME_DIRECT_OTDIH;
    }

    @Override
    protected String exchangeType() {
        return Keys.EXCHANGE_DIRECT;
    }

    @Override
    protected String exchangeRoutingKey() {
        return "document.copy.passport";
    }

    @Override
    protected String queueName() {
        return Keys.EXCHANGE_NAME_DIRECT_OTDIH + "_queue";
    }

    @Override
    protected String templateName() {
        return "direct_otdih.html";
    }
}
