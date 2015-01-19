package com.github.simonthecat.cinema.http.dto;

import com.github.simonthecat.cinema.domain.MoviePlayReservation;

public class MoviePlayReservationDto {

    private String reservationNumber;
    private Long moviePlayId;
    private int seatsTaken;
    private String email;

    public MoviePlayReservationDto(String reservationNumber, Long moviePlayId, int seatsTaken, String email) {
        this.reservationNumber = reservationNumber;
        this.moviePlayId = moviePlayId;
        this.seatsTaken = seatsTaken;
        this.email = email;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public Long getMoviePlayId() {
        return moviePlayId;
    }

    public int getSeatsTaken() {
        return seatsTaken;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "MoviePlayReservationDto{" +
                "reservationNumber='" + reservationNumber + '\'' +
                ", moviePlayId=" + moviePlayId +
                ", seatsTaken=" + seatsTaken +
                ", email='" + email + '\'' +
                '}';
    }

    public static MoviePlayReservationDto fromMoviePlayReservation(MoviePlayReservation moviePlayReservation) {
        return new MoviePlayReservationDto(moviePlayReservation.getReservationNumber(), moviePlayReservation.getMoviePlay().getId(),
                moviePlayReservation.getSeatsTaken(), moviePlayReservation.getEmail());
    }
}
