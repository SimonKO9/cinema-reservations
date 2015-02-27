package com.github.simonthecat.cinema.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.simonthecat.cinema.util.LocalDateTimeJsonSerializer;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movieplays")
public class MoviePlay {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private CinemaHall cinemaHall;

    @Type(type = "com.github.simonthecat.cinema.util.LocalDateTimeUserType")
    @Column
    @JsonSerialize(using = LocalDateTimeJsonSerializer.class)
    private LocalDateTime playDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public LocalDateTime getPlayDate() {
        return playDate;
    }

    public void setPlayDate(LocalDateTime playDate) {
        this.playDate = playDate;
    }


}
