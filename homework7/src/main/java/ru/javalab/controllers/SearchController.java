package ru.javalab.controllers;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javalab.dto.TroupeDto;
import ru.javalab.models.Troupe;
import ru.javalab.repositories.TroupeRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SearchController {

    @Autowired
    private TroupeRepository troupeRepository;

    @GetMapping("/troupes/search")
    public ResponseEntity<List<TroupeDto>> searchByPredicate(@QuerydslPredicate(root = Troupe.class, bindings = TroupeRepository.class) Predicate predicate) {
        return ResponseEntity.ok(
                StreamSupport.stream(troupeRepository.findAll(predicate).spliterator(), true)
                        .map(troupe ->
                                TroupeDto.builder()
                                        .name(troupe.getName())
                                        .state(troupe.getState())
                                        .build()).collect(Collectors.toList()));
    }
}
