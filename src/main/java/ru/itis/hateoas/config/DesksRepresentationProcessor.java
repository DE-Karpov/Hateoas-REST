package ru.itis.hateoas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.controllers.CustomReserveController;
import ru.itis.hateoas.models.Desk;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DesksRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Desk>> {

    private final RepositoryEntityLinks links;

    @Autowired
    public DesksRepresentationProcessor(RepositoryEntityLinks links) {
        this.links = links;
    }

    @Override
    public EntityModel<Desk> process(EntityModel<Desk> model) {
        Desk desk = model.getContent();
        if (desk != null && !desk.getIsReserved()) {
            model.add(linkTo(methodOn(CustomReserveController.class)
                    .reserve(desk.getPlace().getId(), desk.getId()))
                    .withRel("reserve"));
        }
        return model;
    }
}
