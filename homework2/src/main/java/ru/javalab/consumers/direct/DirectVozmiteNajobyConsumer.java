package ru.javalab.consumers.direct;

import ru.javalab.consumers.Consumer;
import ru.javalab.data.Keys;

import java.util.UUID;

public class DirectVozmiteNajobyConsumer extends Consumer {

    @Override
    protected String getFilename() {
        return "direct_vozmite_najoby_" + UUID.randomUUID() + ".pdf";
    }

    @Override
    protected String exchangeName() {
        return Keys.EXCHANGE_NAME_DIRECT_VOZMITE_NAJOBY;
    }

    @Override
    protected String exchangeType() {
        return Keys.EXCHANGE_DIRECT;
    }

    @Override
    protected String exchangeRoutingKey() {
        return "document.original.employmentHistory";
    }

    @Override
    protected String queueName() {
        return Keys.EXCHANGE_NAME_DIRECT_VOZMITE_NAJOBY + "_queue";
    }

    @Override
    protected String templateName() {
        return "direct_vozmite_najoby.html";
    }
}
