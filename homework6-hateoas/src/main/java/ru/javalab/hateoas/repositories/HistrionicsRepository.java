package ru.javalab.hateoas.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.javalab.hateoas.models.Histrionic;

public interface HistrionicsRepository extends PagingAndSortingRepository<Histrionic, String> {
}
