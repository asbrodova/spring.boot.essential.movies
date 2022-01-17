package dev.asbrodova.spring.boot.essential.movies.data.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GENRES")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GENRE_ID")
    private Long genreId;

    @Column(name = "GENRE_TYPE")
    private String genreType;

    @OneToMany(mappedBy = "genre")
    private Set<Movie> movies = new HashSet<>();

    public Long getGenreId() {
        return genreId;
    }

    public String getGenreType() {
        return genreType;
    }

    public void setGenreType(String genreType) {
        this.genreType = genreType;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
