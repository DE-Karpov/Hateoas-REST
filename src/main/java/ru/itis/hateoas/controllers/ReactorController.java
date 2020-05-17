package ru.itis.hateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.itis.hateoas.services.ReserveService;

@RestController
public class ReactorController {

    private final ReserveService reserveService;

    @Autowired
    public ReactorController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @GetMapping(value = "/reactor",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> getAllRestaurants(){
        return reserveService.getAllRestaurants();
    }
}
