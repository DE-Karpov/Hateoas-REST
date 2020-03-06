package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Customer;

public interface CustomersRepository extends JpaRepository<Customer, Long> {
}
