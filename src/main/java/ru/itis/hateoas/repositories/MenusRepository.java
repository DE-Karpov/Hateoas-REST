package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Menu;

public interface MenusRepository extends JpaRepository<Menu, Long> {
}
