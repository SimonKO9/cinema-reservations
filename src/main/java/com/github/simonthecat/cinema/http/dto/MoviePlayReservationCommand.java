package com.github.simonthecat.cinema.http.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MoviePlayReservationCommand {

    private int seatsTaken;
    private String email;

    @JsonCreator
    public MoviePlayReservationCommand(@JsonProperty("seatsTaken") int seatsTaken, @JsonProperty("email") String email) {
        this.seatsTaken = seatsTaken;
        this.email = email;
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
                ", seatsTaken=" + seatsTaken +
                ", email='" + email + '\'' +
                '}';
    }

}
