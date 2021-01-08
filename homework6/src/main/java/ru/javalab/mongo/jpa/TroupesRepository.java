package ru.javalab.mongo.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TroupesRepository extends MongoRepository<Troupe, String> {

    @Query(value = "{inAct: true, $or: [{keywords: ?keywords}, {plannedCitiesNum: {$gt: ?1}}]}")
    List<Troupe> find(@Param("keywords") List<String> keywords, @Param("plannedCitiesNum") int maximumPlannedCitiesNum);
}
