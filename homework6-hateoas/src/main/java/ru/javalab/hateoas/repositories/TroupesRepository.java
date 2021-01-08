package ru.javalab.hateoas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.javalab.hateoas.models.Troupe;

import java.awt.print.Pageable;
import java.util.List;

public interface TroupesRepository extends MongoRepository<Troupe, String> {

    @RestResource(path = "troupe", rel = "troupe")
    @Query(value = "{inAct: true, $or: [{keywords: ?keywords}, {plannedCitiesNum: {$gt: ?1}}]}")
    List<Troupe> find(@Param("keywords") List<String> keywords, @Param("plannedCitiesNum") int maximumPlannedCitiesNum, Pageable pageable);
}
