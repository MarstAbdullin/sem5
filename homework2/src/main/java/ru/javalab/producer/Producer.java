package ru.javalab.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.javalab.data.Keys;
import ru.javalab.models.DocumentDto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) {

        while (true) {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(Keys.HOST_NAME);

            List<DocumentDto> documentDtos = Keys.DOCUMENT_DTOS;

            System.out.println("Enter the number of what you need:");
            for (int i = 0; i < documentDtos.size(); i++) {
                System.out.printf("%s %s%n", i + 1, documentDtos.get(i).getName());
            }

            Scanner scanner = new Scanner(System.in);
            int num = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the number of your " + documentDtos.get(num - 1).getDocumentType());
            String line = scanner.nextLine();
            String routingKey = documentDtos.get(num - 1).getDocumentCode();

            try (Connection connection = connectionFactory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.basicPublish(Keys.EXCHANGE_NAME_FANOUT, routingKey, null, line.getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(Keys.EXCHANGE_NAME_DIRECT_CREDIT, routingKey, null, line.getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(Keys.EXCHANGE_NAME_DIRECT_BOLEYU, routingKey, null, line.getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(Keys.EXCHANGE_NAME_DIRECT_VOZMITE_NAJOBY, routingKey, null, line.getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(Keys.EXCHANGE_NAME_DIRECT_OTDIH, routingKey, null, line.getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(Keys.EXCHANGE_NAME_TOPIC, routingKey, null, line.getBytes(StandardCharsets.UTF_8));
            } catch (IOException | TimeoutException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }


}
