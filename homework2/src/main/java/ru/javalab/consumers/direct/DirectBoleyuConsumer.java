package ru.javalab.consumers.direct;

import ru.javalab.consumers.Consumer;
import ru.javalab.data.Keys;

import java.util.UUID;

public class DirectBoleyuConsumer extends Consumer {

    @Override
    protected String getFilename() {
        return "direct_boleyu_" + UUID.randomUUID() + ".pdf";
    }

    @Override
    protected String exchangeName() {
        return Keys.EXCHANGE_NAME_DIRECT_BOLEYU;
    }

    @Override
    protected String exchangeType() {
        return Keys.EXCHANGE_DIRECT;
    }

    @Override
    protected String exchangeRoutingKey() {
        return "document.original.bolnichny";
    }

    @Override
    protected String queueName() {
        return Keys.EXCHANGE_NAME_DIRECT_BOLEYU + "_queue";
    }

    @Override
    protected String templateName() {
        return "direct_boleyu.html";
    }


}
