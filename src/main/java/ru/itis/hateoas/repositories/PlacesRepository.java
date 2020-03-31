package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Place;

import java.util.Optional;

public interface PlacesRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByName(String name);

}
