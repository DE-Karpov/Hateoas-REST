package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Dish;

public interface DishesRepository extends JpaRepository<Dish, Long> {
}
