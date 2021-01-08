package ru.javalab.repositories;

import ru.javalab.models.Troupe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface TroupeRepository extends PagingAndSortingRepository<Troupe, Long>  {

    @RestResource(path = "published", rel = "published")
    @Query("from Troupe troupe where troupe.state = 'Published'")
    Page<Troupe> findAllPublished(Pageable pageable);

    @RestResource(path = "name", rel = "name")
    List<Troupe> findAllByName(String name);
}

