package dev.asbrodova.spring.boot.essential.movies.web;

import dev.asbrodova.spring.boot.essential.movies.business.service.CatalogueService;
import dev.asbrodova.spring.boot.essential.movies.data.entity.Genre;
import dev.asbrodova.spring.boot.essential.movies.data.entity.Movie;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(MoviesWebController.class)
public class MoviesWebControllerTest {

    @MockBean
    private CatalogueService catalogueServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldListAllMovies_whenGetListOfAllMovies_givenMockBehavior() throws Exception {
        //given
        List<Movie> givenMoviesList = new ArrayList<>();

        Movie givenMovie = new Movie();
        givenMovie.setMovieId(1l);
        givenMovie.setMovieName("Friends");
        givenMovie.setYear(1990);

        Genre givenGerne = new Genre();
        givenGerne.setGenreId(1l);
        givenGerne.setGenreType("Comedy");
        givenGerne.setMovies(new HashSet<Movie>(Arrays.asList(givenMovie)));

        givenMovie.setGenre(givenGerne);

        givenMoviesList.add(givenMovie);

        given(catalogueServiceMock.listAllMovies()).willReturn(givenMoviesList);

        //when/then
        mockMvc.perform(get("/all/movies"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Friends")));
    }
}
