package ru.itis.hateoas.services;

import ru.itis.hateoas.models.Desk;

public interface ReserveService {
    Desk reserve(Long placeId, Long deskId);
}
