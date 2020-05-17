package ru.itis.hateoas.clients;

import reactor.core.publisher.Mono;

public interface RestaurantClient {
    Mono<String> getAll();
}
