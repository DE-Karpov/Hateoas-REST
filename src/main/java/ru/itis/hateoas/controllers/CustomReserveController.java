package ru.itis.hateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.services.ReserveService;

@RepositoryRestController
public class CustomReserveController {

    private final ReserveService reserveService;

    @Autowired
    public CustomReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @PutMapping("/places/{place-id}/desks/{desk-id}/reserve")
    public @ResponseBody
    ResponseEntity<?> reserve(@PathVariable("place-id") final Long placeId,
                              @PathVariable("desk-id") final Long deskId) {
        return ResponseEntity.ok(
                new EntityModel<>(
                        reserveService.reserve(placeId, deskId)));
    }
}
