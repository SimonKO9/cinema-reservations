package com.github.simonthecat.cinema.http.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.simonthecat.cinema.domain.MoviePlay;
import com.github.simonthecat.cinema.util.LocalDateTimeJsonSerializer;

import java.time.LocalDateTime;

public class MoviePlayDto {

    private Long id;
    private Long movieId;
    private String cinemaHall;
    private int maxSeats;
    @JsonSerialize(using = LocalDateTimeJsonSerializer.class)
    private LocalDateTime playDate;

    public MoviePlayDto(Long id, Long movieId, String cinemaHall, int maxSeats, LocalDateTime playDate) {
        this.id = id;
        this.movieId = movieId;
        this.cinemaHall = cinemaHall;
        this.maxSeats = maxSeats;
        this.playDate = playDate;
    }

    public Long getId() {
        return id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public String getCinemaHall() {
        return cinemaHall;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public LocalDateTime getPlayDate() {
        return playDate;
    }

    public static MoviePlayDto fromMoviePlay(MoviePlay moviePlay) {
        return new MoviePlayDto(moviePlay.getId(), moviePlay.getMovie().getId(), moviePlay.getCinemaHall().getKey(), moviePlay.getCinemaHall().getSeats(), moviePlay.getPlayDate());
    }
}
