package ru.itis.hateoas;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ru.itis.hateoas.repositories.PlacesRepository;
import ru.itis.hateoas.repositories.TablesRepository;

@RepositoryRestController
public class CustomCoursesController {

    private final PlacesRepository placesRepository;
    private final TablesRepository tablesRepository;

    @Autowired
    public CustomCoursesController(PlacesRepository placesRepository, TablesRepository tablesRepository) {
        this.placesRepository = placesRepository;
        this.tablesRepository = tablesRepository;
    }

    @PutMapping("/places/{place-id}/tables/{table-id}/reserve")
    public ResponseEntity<?> reserve(@PathVariable("place-id") final Long placeId,
                                     @PathVariable("table-id") final Long tableId) {
        val place = placesRepository.getOne(placeId);
        val table = place.getDesks().get(Math.toIntExact(tableId - 1));
        if (!table.getIsReserved()) {
            table.reserve();
            tablesRepository.save(table);
            System.out.println("Well done, table is reserved!");
        } else {
            System.err.println("Table is already reserved");
        }
        return ResponseEntity.ok().build();
    }
}
