package ru.javalab.homework1.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.javalab.homework1.dto.DocumentDto;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final static String EXCHANGE_NAME = "documents";
    private final static String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Введите в строку через пробел следующие данные: имя фамилия серия_паспорта номер_паспорта дата_выдачи");
                System.out.println("Пример: Familia Imya 345678 12.12.2012");
                String info = scanner.nextLine();

                    DocumentDto documentDto = Parser.parse(info);
                    ObjectMapper mapper = new ObjectMapper();
                    String json = mapper.writeValueAsString(documentDto);
                    channel.basicPublish(EXCHANGE_NAME, "", null, json.getBytes());


            }
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }


}
