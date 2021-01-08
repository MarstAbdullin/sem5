package ru.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.javalab.services.TroupeService;

@RepositoryRestController
public class TroupeController {

    @Autowired
    private TroupeService troupeService;

    @RequestMapping(value = "/troupe/{troupeId}/publish", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> publish(@PathVariable("troupeId") Long troupeId) {
        return ResponseEntity.ok(
                EntityModel.of(troupeService.publish(troupeId)));
    }
}
