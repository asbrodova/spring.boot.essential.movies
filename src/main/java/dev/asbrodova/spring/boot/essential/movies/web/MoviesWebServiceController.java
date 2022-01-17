package dev.asbrodova.spring.boot.essential.movies.web;

import dev.asbrodova.spring.boot.essential.movies.business.service.CatalogueService;
import dev.asbrodova.spring.boot.essential.movies.data.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/all/movies")
public class MoviesWebServiceController {

    private CatalogueService catalogueService;

    @Autowired
    public MoviesWebServiceController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return catalogueService.listAllMovies();
    }
}
