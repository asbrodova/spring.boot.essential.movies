package dev.asbrodova.spring.boot.essential.movies.controllers;

import dev.asbrodova.spring.boot.essential.movies.business.service.CatalogueService;
import dev.asbrodova.spring.boot.essential.movies.data.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MoviesWebController {

    private final CatalogueService catalogueService;

    @Autowired
    public MoviesWebController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @GetMapping
    public String getListOfMovies(@RequestParam(value = "genre", required = false) String genreType, Model model) {

        List<Movie> moviesList = null;

        if (StringUtils.hasText(genreType)) {
            moviesList = catalogueService.listMoviesByGenreType(genreType);
        } else {
            moviesList = catalogueService.listAllMovies();
        }
        model.addAttribute("moviesList", moviesList);
        return "movies";
    }

    @GetMapping("/twentyOneCentury")
    public String getListOfMoviesOfTwentyOneCentury(Model model){
        List<Movie> moviesList = catalogueService.listMoviesOfTwentyOneCentury();
        model.addAttribute("moviesList", moviesList);
        return "movies";
    }
}
