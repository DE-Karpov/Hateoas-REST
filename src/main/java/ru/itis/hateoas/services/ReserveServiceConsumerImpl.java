package ru.itis.hateoas.services;

import lombok.val;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.repositories.DesksRepository;
import ru.itis.hateoas.repositories.PlacesRepository;

@Service
public class ReserveServiceConsumerImpl implements ReserveServiceConsumer {

    private static final String EXCHANGE_NAME = "messages";
    private static final String QUEUE_1 = "queue_1";

    private final DesksRepository desksRepository;
    private final PlacesRepository placesRepository;

    @Autowired
    public ReserveServiceConsumerImpl(final DesksRepository desksRepository, PlacesRepository placesRepository) {
        this.desksRepository = desksRepository;
        this.placesRepository = placesRepository;
    }

    @Override
    @RabbitListener(bindings = @QueueBinding( value = @Queue(value = QUEUE_1),
            exchange = @Exchange(value = EXCHANGE_NAME, type = ExchangeTypes.FANOUT)))
    public void reserve(byte[] data) {
        String consumedMessage = new String(data);
        Long placeId = Long.valueOf(consumedMessage.split(" ")[0]);
        Long deskId = Long.valueOf(consumedMessage.split(" ")[1]);
        val desk = desksRepository.findByPlaceIdAndNumber(placeId, deskId).orElseThrow(IllegalArgumentException::new);
        val place = desk.getPlace();
        desk.reserve();
        place.isFull();
        desksRepository.save(desk);
        placesRepository.save(place);
    }
}
