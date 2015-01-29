package com.github.simonthecat.cinema.http.rs;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class MoviePlayReservationParameters {

    private String movieTitle;
    private String hallKey;
    private String dateFrom;
    private String dateTo;

    public MoviePlayReservationParameters() {
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
        return tryToDate(dateFrom);
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTo() {
        return tryToDate(dateTo);
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    private LocalDateTime tryToDate(String millisStr) {
        try {
            Long millis = Long.valueOf(millisStr);
            LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.of("UTC"));
            return dateTime;
        } catch(NumberFormatException nfe) {
            return null;
        }
    }

}
