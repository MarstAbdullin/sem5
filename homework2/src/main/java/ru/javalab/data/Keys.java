package ru.javalab.data;

import ru.javalab.models.DocumentDto;

import java.util.Arrays;
import java.util.List;

public class Keys {

    public final static String EXCHANGE_NAME_DIRECT_BOLEYU = "documents_direct_boleyu";
    public final static String EXCHANGE_NAME_DIRECT_CREDIT = "documents_direct_credit";
    public final static String EXCHANGE_NAME_DIRECT_VOZMITE_NAJOBY = "documents_direct_vozmite_najoby";
    public final static String EXCHANGE_NAME_DIRECT_OTDIH = "documents_direct_otdih";

    public final static String EXCHANGE_NAME_FANOUT = "documents_fanout";
    public final static String EXCHANGE_NAME_TOPIC = "documents_topic";

    public final static String EXCHANGE_FANOUT = "fanout";
    public final static String EXCHANGE_DIRECT = "direct";
    public final static String EXCHANGE_TOPIC = "topic";

    public final static String HOST_NAME = "localhost";

    public final static List<DocumentDto> DOCUMENT_DTOS = Arrays.asList(
            new DocumentDto("Uiti na bolnichny", "number of hospital", "document.original.bolnichny"),
            new DocumentDto("Get credit", "number of passport", "document.original.passport"),
            new DocumentDto("Hochu joboty", "number of employment history", "document.original.employmentHistory"),
            new DocumentDto("Hochu otdyh", "number of passport", "document.copy.passport")
    );

}
