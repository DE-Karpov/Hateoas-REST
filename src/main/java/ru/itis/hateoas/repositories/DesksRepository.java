package ru.itis.hateoas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.models.Desk;

import java.util.Optional;

@RepositoryRestResource
public interface DesksRepository extends PagingAndSortingRepository<Desk, Long> {

    @Query("from Desk desk where desk.place.id = ?1 and desk.id = ?2")
    Optional<Desk> findByPlaceIdAndNumber(Long placeId, Long id);

    @RestResource(path = "reserved", rel = "findAllReserved")
    @Query("from Desk desk where desk.isReserved = true ")
    Page<Desk> findAllReserved(Pageable pageable);

}
