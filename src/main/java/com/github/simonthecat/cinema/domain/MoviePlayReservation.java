package com.github.simonthecat.cinema.domain;

import javax.persistence.*;

@Entity
@Table(name = "movieplayreservations")
public class MoviePlayReservation {

    @Id
    @Column(name = "reservation_number")
    private String reservationNumber;

    @ManyToOne
    private MoviePlay moviePlay;

    @Column(name = "seats_taken")
    private int seatsTaken;

    @Column(name = "email")
    private String email;

    public MoviePlayReservation() {
    }

    public MoviePlayReservation(String reservationNumber, MoviePlay moviePlay, int seatsTaken, String email) {
        this.reservationNumber = reservationNumber;
        this.moviePlay = moviePlay;
        this.seatsTaken = seatsTaken;
        this.email = email;
    }

    public MoviePlay getMoviePlay() {
        return moviePlay;
    }

    public void setMoviePlay(MoviePlay moviePlay) {
        this.moviePlay = moviePlay;
    }

    public int getSeatsTaken() {
        return seatsTaken;
    }

    public void setSeatsTaken(int seatsTaken) {
        this.seatsTaken = seatsTaken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

}
