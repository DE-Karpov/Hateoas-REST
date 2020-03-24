package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Place;

public interface PlacesRepository extends JpaRepository<Place, Long> {
    Place findByName(String name);
}
