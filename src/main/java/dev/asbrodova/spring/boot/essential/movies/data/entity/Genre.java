package dev.asbrodova.spring.boot.essential.movies.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "GENRES")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GENRE_ID")
    private Long genreId;

    @Column(name = "GENRE_TYPE")
    private String genreType;

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getGenreType() {
        return genreType;
    }

    public void setGenreType(String genreType) {
        this.genreType = genreType;
    }
}
