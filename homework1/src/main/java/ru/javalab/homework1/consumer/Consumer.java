package ru.javalab.homework1.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ru.javalab.homework1.dto.DocumentDto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Consumer {

    private final static String EXCHANGE_NAME = "documents";
    private final static String EXCHANGE_TYPE = "fanout";


    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();

            channel.queueBind(queue, EXCHANGE_NAME, "");
            System.out.println("Введите тему документа");
            String theme = new Scanner(System.in).nextLine();
            ObjectMapper objectMapper = new ObjectMapper();
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                DocumentDto documentDto = objectMapper.readValue(message.getBody(), DocumentDto.class);
                try {
                    Document document = new Document();
                    File file = new File("docs/" + theme +".pdf");
                    file.createNewFile();
                    PdfWriter.getInstance(document, new FileOutputStream(file));
                    document.open();
                    PdfCreator.generatePdf(theme, documentDto, document);
                    document.close();
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (DocumentException e) {
                    System.out.println(e);
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                    System.err.println("Document creating failed");
                }
            };

            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}