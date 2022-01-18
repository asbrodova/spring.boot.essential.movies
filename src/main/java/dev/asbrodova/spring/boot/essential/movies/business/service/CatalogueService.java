package dev.asbrodova.spring.boot.essential.movies.business.service;

import dev.asbrodova.spring.boot.essential.movies.data.entity.Movie;
import dev.asbrodova.spring.boot.essential.movies.data.repository.GenreRepository;
import dev.asbrodova.spring.boot.essential.movies.data.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogueService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public CatalogueService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    /**
     * Returns a list of all {@link Movie} that are in db
     *
     * @return java.util.List of all the Movies
     */
    public List<Movie> listAllMovies() {

        List<Movie> moviesList = new ArrayList<>();
        Iterable<Movie> movies = movieRepository.findAll();
        movies.forEach(moviesList::add);

        return moviesList;
    }

    /**
     * Returns a list of all {@link Movie} with given GenreType
     *
     * @param genreType
     * @return java.util.List of Movies
     */
    public List<Movie> listMoviesByGenreType(String genreType) {

        List<Movie> moviesList = new ArrayList<>();
        Iterable<Movie> movies = movieRepository.findMoviesByGenreGenreType(genreType);
        movies.forEach(moviesList::add);

        return moviesList;
    }

    /**
     * Returns a list of all {@link Movie} of 21 century. As an example of repo method with @Query
     *
     * @return java.util.List of Movies
     */
    public List<Movie> listMoviesOfTwentyOneCentury() {

        List<Movie> moviesList = new ArrayList<>();
        Iterable<Movie> movies = movieRepository.findMoviesOfTwentyOnesCentury();
        movies.forEach(moviesList::add);

        return moviesList;
    }
}
