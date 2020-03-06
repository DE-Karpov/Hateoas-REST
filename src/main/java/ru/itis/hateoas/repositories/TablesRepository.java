package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Desk;

public interface TablesRepository extends JpaRepository<Desk, Long> {
}
