package ru.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javalab.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
