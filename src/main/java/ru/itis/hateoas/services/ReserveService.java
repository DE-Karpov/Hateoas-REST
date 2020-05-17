package ru.itis.hateoas.services;

import reactor.core.publisher.Mono;
import ru.itis.hateoas.models.Desk;


public interface ReserveService {
    Desk reserve(Long placeId, Long deskId);

    Mono<String> getAllRestaurants();
}
