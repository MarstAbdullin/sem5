package ru.javalab.consumers.direct;

import ru.javalab.consumers.Consumer;
import ru.javalab.data.Keys;

import java.util.UUID;

public class DirectCreditConsumer extends Consumer {

    @Override
    protected String getFilename() {
        return "direct_credit_" + UUID.randomUUID() + ".pdf";
    }

    @Override
    protected String exchangeName() {
        return Keys.EXCHANGE_NAME_DIRECT_CREDIT;
    }

    @Override
    protected String exchangeType() {
        return Keys.EXCHANGE_DIRECT;
    }

    @Override
    protected String exchangeRoutingKey() {
        return "document.original.passport";
    }

    @Override
    protected String queueName() {
        return Keys.EXCHANGE_NAME_DIRECT_CREDIT + "_queue";
    }

    @Override
    protected String templateName() {
        return "direct_credit.html";
    }

}
