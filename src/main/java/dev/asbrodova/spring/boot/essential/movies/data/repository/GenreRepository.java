package dev.asbrodova.spring.boot.essential.movies.data.repository;

import dev.asbrodova.spring.boot.essential.movies.data.entity.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
}
