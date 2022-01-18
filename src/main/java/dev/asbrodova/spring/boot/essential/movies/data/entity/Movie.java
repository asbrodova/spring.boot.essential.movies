package dev.asbrodova.spring.boot.essential.movies.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "MOVIES")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIE_ID")
    private Long movieId;

    @Column(name = "MOVIE_NAME")
    private String movieName;

    @Column(name = "YEAR")
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "genreId")
    private Genre genre;

    public Long getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
