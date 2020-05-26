package ru.itis.hateoas.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReserveServiceProducerImpl implements ReserveServiceProducer {

    private final AmqpTemplate rabbitTemplate;

    @Value("${rabbitmq.routingkey1}")
    private String ROUTING_KEY;

    @Value("${rabbitmq.exchange}")
    private String EXCHANGE_NAME;

    @Autowired
    public ReserveServiceProducerImpl(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void reserve(final Long placeId, final Long deskId) {
        String message = getString(placeId, deskId);
        log.info("[MESSAGE SENDER] Message will be sent with object to consumer");
        log.info("[MESSAGE SENDER] Object: " + message);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,ROUTING_KEY, message);
    }

    private String getString(final Long placeId, final Long deskId){
        return placeId + " " + deskId;
    }

}
