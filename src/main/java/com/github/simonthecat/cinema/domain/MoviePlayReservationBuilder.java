package com.github.simonthecat.cinema.domain;

public class MoviePlayReservationBuilder {
    private String reservationNumber;
    private MoviePlay moviePlay;
    private int seatsTaken;
    private String email;

    public MoviePlayReservationBuilder setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
        return this;
    }

    public MoviePlayReservationBuilder setMoviePlay(MoviePlay moviePlay) {
        this.moviePlay = moviePlay;
        return this;
    }

    public MoviePlayReservationBuilder setSeatsTaken(int seatsTaken) {
        this.seatsTaken = seatsTaken;
        return this;
    }

    public MoviePlayReservationBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public MoviePlayReservation build() {
        return new MoviePlayReservation(reservationNumber, moviePlay, seatsTaken, email);
    }
}