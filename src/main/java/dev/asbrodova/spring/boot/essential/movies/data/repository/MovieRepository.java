package dev.asbrodova.spring.boot.essential.movies.data.repository;

import dev.asbrodova.spring.boot.essential.movies.data.entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    Iterable<Movie> findMoviesByGenreGenreType(String genreType);

    @Query("select m from Movie m where m.year > 1999")
    Iterable<Movie> findMoviesOfTwentyOnesCentury();
}
