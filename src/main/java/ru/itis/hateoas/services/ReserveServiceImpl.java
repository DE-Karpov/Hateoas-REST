package ru.itis.hateoas.services;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.models.Desk;
import ru.itis.hateoas.repositories.DesksRepository;
import ru.itis.hateoas.repositories.PlacesRepository;

@Service
public class ReserveServiceImpl implements ReserveService {

    private final DesksRepository desksRepository;
    private final PlacesRepository placesRepository;

    @Autowired
    public ReserveServiceImpl(final DesksRepository desksRepository, PlacesRepository placesRepository) {
        this.desksRepository = desksRepository;
        this.placesRepository = placesRepository;
    }

    @Override
    public Desk reserve(final Long placeId, final Long deskId) {
        val desk = desksRepository.findByPlaceIdAndNumber(placeId, deskId).orElseThrow(IllegalArgumentException::new);
        val place = desk.getPlace();
        desk.reserve();
        place.isFull();
        desksRepository.save(desk);
        placesRepository.save(place);
        return desk;
    }
}
