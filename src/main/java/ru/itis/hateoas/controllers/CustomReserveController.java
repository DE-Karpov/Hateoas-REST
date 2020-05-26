package ru.itis.hateoas.controllers;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.services.ReserveServiceProducer;

@RepositoryRestController
public class CustomReserveController {

    private final ReserveServiceProducer reserveServiceProducer;

    @Autowired
    public CustomReserveController(ReserveServiceProducer reserveServiceProducer) {
        this.reserveServiceProducer = reserveServiceProducer;
    }

    @PutMapping("/places/{place-id}/desks/{desk-id}/reserve")
    public ResponseEntity<?> reserve(@PathVariable("place-id") final Long placeId,
                              @PathVariable("desk-id") final Long deskId) {
        reserveServiceProducer.reserve(placeId, deskId);
        return ResponseEntity.ok().build();
    }
}
