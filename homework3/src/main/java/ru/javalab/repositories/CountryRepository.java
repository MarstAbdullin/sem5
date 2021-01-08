package ru.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javalab.models.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
