package ru.itis.hateoas.services;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.models.Desk;
import ru.itis.hateoas.repositories.DesksRepository;

@Service
public class ReserveServiceImpl implements ReserveService {

    private final DesksRepository desksRepository;

    @Autowired
    public ReserveServiceImpl(final DesksRepository desksRepository) {
        this.desksRepository = desksRepository;
    }

    @Override
    public Desk reserve(final Long placeId, final Long deskNumber) {
        val desk = desksRepository.findByPlaceIdAndNumber(placeId, deskNumber).orElseThrow(IllegalArgumentException::new);
        desk.reserve();
        desksRepository.save(desk);
        return desk;
    }
}
