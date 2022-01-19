package dev.asbrodova.spring.boot.essential.movies.web;

import dev.asbrodova.spring.boot.essential.movies.business.service.CatalogueService;
import dev.asbrodova.spring.boot.essential.movies.data.entity.Genre;
import dev.asbrodova.spring.boot.essential.movies.data.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
     * Endpoint example for local testing: localhost:8080/api/create/movie?movieName=Sherlock&year=2003&genreType=Action
     *
     * @param movieName
     * @param year
     * @param genreType
     * @return String representation of id of created Movie or warn msq in case of no genre exists
     */
    @GetMapping(value = "/create/movie")
    public String createMovieViaGet(@RequestParam String movieName,
                                    @RequestParam String year,
                                    @RequestParam String genreType) {
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

    /**
     * Example of POST request for local testing:
     * <p>
     * http://localhost:8080/api/create/movie?Content-Type=application/json; charset=UTF-8
     * <p>
     * body:
     * {"movieName":"Silence","year":2005,"genre":{"genreId":3}}
     *
     * @param movie
     * @return HttpStatus
     */
    @RequestMapping(value = "/create/movie", method = RequestMethod.POST)
    public ResponseEntity<String> createMovieViaPost(@RequestBody Movie movie) {
        if (catalogueService.movieValid(movie)) {

            Optional<Genre> genre = catalogueService.getGenreById(movie.getGenre().getGenreId());

            if (genre.isPresent()) {
                movie.setGenre(genre.get());
                catalogueService.createMovie(movie);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}
