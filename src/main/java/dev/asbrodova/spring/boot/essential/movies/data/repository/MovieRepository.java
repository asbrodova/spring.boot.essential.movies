package dev.asbrodova.spring.boot.essential.movies.data.repository;

import dev.asbrodova.spring.boot.essential.movies.data.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
}
