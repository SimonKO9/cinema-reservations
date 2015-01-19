package com.github.simonthecat.cinema.http.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.simonthecat.cinema.domain.Movie;

public class MovieDto {

    private Long id;

    private String title;

    private String description;

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
    }

    @JsonCreator
    public MovieDto(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieDto movieDto = (MovieDto) o;

        if (description != null ? !description.equals(movieDto.description) : movieDto.description != null)
            return false;
        if (id != null ? !id.equals(movieDto.id) : movieDto.id != null) return false;
        if (title != null ? !title.equals(movieDto.title) : movieDto.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
