package dev.asbrodova.spring.boot.essential.movies.web;

import dev.asbrodova.spring.boot.essential.movies.business.service.CatalogueService;
import dev.asbrodova.spring.boot.essential.movies.data.entity.Genre;
import dev.asbrodova.spring.boot.essential.movies.data.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MoviesWebServiceController {

    static final String NO_GENRE_INFO_MESSAGE = "%s is not an allowed genre";

    private CatalogueService catalogueService;

    @Autowired
    public MoviesWebServiceController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @GetMapping("/all/movies")
    public List<Movie> getAllMovies() {
        return catalogueService.listAllMovies();
    }

    /**
     * Endpoint example for local testing: localhost:8080/api/create/movie?movieName=Sherlock&year=2003&genre=Action
     *
     * @param movieName
     * @param year
     * @param genreType
     * @return id of created Movie
     */
    @GetMapping(value = "/create/movie")
    public String createMovie(@RequestParam(value = "movieName", required = true) String movieName,
                              @RequestParam(value = "year", required = true) String year,
                              @RequestParam(value = "genre", required = true) String genreType) {
        Movie movie = new Movie();
        movie.setMovieName(movieName);
        movie.setYear(Integer.parseInt(year));
        if (catalogueService.genreExists(genreType)) {

            Genre genre = catalogueService.getGenreByType(genreType);
            movie.setGenre(genre);

            return catalogueService.createMovie(movie).getMovieId().toString();
        } else {
            return String.format(NO_GENRE_INFO_MESSAGE, genreType);
        }
    }
}
