package ru.javalab.consumers;

import ru.javalab.consumers.direct.DirectCreditConsumer;
import ru.javalab.consumers.direct.DirectBoleyuConsumer;
import ru.javalab.consumers.direct.DirectVozmiteNajobyConsumer;
import ru.javalab.consumers.direct.DirectOtgihConsumer;
import ru.javalab.consumers.fanout.FanoutCounterConsumer;
import ru.javalab.consumers.topic.TopicCopiesCounterConsumer;

public class Main {

    public static void main(String[] args) {
        new DirectBoleyuConsumer().run();
        new DirectCreditConsumer().run();
        new DirectVozmiteNajobyConsumer().run();
        new DirectOtgihConsumer().run();
        new TopicCopiesCounterConsumer().run();
        new FanoutCounterConsumer().run();
    }

}
