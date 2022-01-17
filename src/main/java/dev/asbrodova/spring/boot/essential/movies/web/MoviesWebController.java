package dev.asbrodova.spring.boot.essential.movies.web;

import dev.asbrodova.spring.boot.essential.movies.business.service.CatalogueService;
import dev.asbrodova.spring.boot.essential.movies.data.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/all/movies")
public class MoviesWebController {

    private final CatalogueService catalogueService;

    @Autowired
    public MoviesWebController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @GetMapping
    public String getListOfAllMovies(Model model) {
        List<Movie> moviesList = catalogueService.listAllMovies();
        model.addAttribute("moviesList", moviesList);
        return "allMovies";
    }
}
