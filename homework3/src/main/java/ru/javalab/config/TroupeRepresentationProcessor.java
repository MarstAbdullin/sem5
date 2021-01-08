package ru.javalab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ru.javalab.controllers.TroupeController;
import ru.javalab.models.Troupe;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TroupeRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Troupe>> {

    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Troupe> process(EntityModel<Troupe> model) {
        Troupe troupe = model.getContent();
        if (troupe != null && troupe.getState().equals("Planned")) {
            model.add(WebMvcLinkBuilder.linkTo(methodOn(TroupeController.class)
                    .publish(troupe.getId())).withRel("publish"));
        }

        if (troupe != null && troupe.getState().equals("Published")) {
            model.add(links.linkToItemResource(Troupe.class, troupe.getId()).
                    withRel("delete"));
        }
        return model;
    }
}
