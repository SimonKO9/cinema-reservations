package com.github.simonthecat.cinema.http.rs;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class SearchReservationParameters {

    private String movieTitle;
    private String hallKey;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private String reservationId;
    private String email;

    public SearchReservationParameters() {
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getEmail() {
        return email;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getHallKey() {
        return hallKey;
    }

    public void setHallKey(String hallKey) {
        this.hallKey = hallKey;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        LocalDateTime dateTime = tryToDate(dateFrom);
        if(dateTime != null) {
            this.dateFrom = dateTime.withHour(0).withMinute(0).withSecond(0);
        }
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        LocalDateTime dateTime = tryToDate(dateTo);
        if(dateTime != null) {
            this.dateTo = dateTime.withHour(23).withMinute(59).withSecond(59);
        }
    }

    private LocalDateTime tryToDate(String millisStr) {
        try {
            Long millis = Long.valueOf(millisStr);
            LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.of("UTC"));
            return dateTime;
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SearchReservationParameters{" +
                "movieTitle='" + movieTitle + '\'' +
                ", hallKey='" + hallKey + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", reservationId='" + reservationId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
