package com.github.simonthecat.cinema.http.dto;

import com.github.simonthecat.cinema.domain.CinemaHall;

public class CinemaHallDto {
    private String key;
    private int seats;

    public CinemaHallDto(String key, int seats) {
        this.key = key;
        this.seats = seats;
    }

    public String getKey() {
        return key;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CinemaHallDto that = (CinemaHallDto) o;

        if (seats != that.seats) return false;
        if (key != null ? !key.equals(that.key) : that.key != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + seats;
        return result;
    }

    @Override
    public String toString() {
        return "CinemaHallDto{" +
                "key='" + key + '\'' +
                ", seats=" + seats +
                '}';
    }

    public static CinemaHallDto fromDto(CinemaHall cinemaHall) {
        return new CinemaHallDto(cinemaHall.getKey(), cinemaHall.getSeats());
    }
}
