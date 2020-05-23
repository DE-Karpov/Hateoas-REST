package ru.itis.hateoas.services;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class ReserveServiceProducerImpl implements ReserveServiceProducer {

    private final static String EXCHANGE_TYPE = "fanout";
    private static final String EXCHANGE_NAME = "messages";
    private static final String QUEUE_1 = "queue_1";

    @Override
    public void reserve(final Long placeId, final Long deskId) {

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE,true);

            String message = getString(placeId, deskId);

            channel.basicPublish(EXCHANGE_NAME, QUEUE_1, null, message.getBytes());

            channel.close();
            connection.close();
        } catch (IOException io) {
            System.out.println("IOException");
            io.printStackTrace();
        } catch (TimeoutException toe) {
            System.out.println("TimeoutException : " + toe.getMessage());
            toe.printStackTrace();
        }
    }

    private String getString(final Long placeId, final Long deskId){
        return placeId + " " + deskId;
    }

}
