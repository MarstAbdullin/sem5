package ru.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javalab.models.Histrionic;

public interface HistrionicRepository extends JpaRepository<Histrionic, Long> {
}
