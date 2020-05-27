package ru.itis.hateoas.services;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.repositories.DesksRepository;
import ru.itis.hateoas.repositories.PlacesRepository;

@Service
@Slf4j
public class ReserveServiceConsumerImpl implements ReserveServiceConsumer {

    private final DesksRepository desksRepository;
    private final PlacesRepository placesRepository;

    @Autowired
    public ReserveServiceConsumerImpl(final DesksRepository desksRepository, PlacesRepository placesRepository) {
        this.desksRepository = desksRepository;
        this.placesRepository = placesRepository;
    }

    @Override
    @RabbitListener(queues = "queue_1")
    public void reserve(String data) {
        log.info("[RESERVE] Received message with params [{}] from [{}]", data, "queue_1");
        Long placeId = Long.valueOf(data.split(" ")[0]);
        Long deskId = Long.valueOf(data.split(" ")[1]);
        val desk = desksRepository.findByPlaceIdAndNumber(placeId, deskId).orElseThrow(IllegalArgumentException::new);
        val place = desk.getPlace();
        desk.reserve();
        place.isFull();
        desksRepository.save(desk);
        placesRepository.save(place);
    }
}
