package com.github.simonthecat.cinema.domain.service;

import com.github.simonthecat.cinema.domain.MoviePlayReservation;
import com.github.simonthecat.cinema.http.rs.SearchReservationParameters;

import java.util.List;

public interface MoviePlayService {

    List<MoviePlayReservation> findReservations(SearchReservationParameters parameters);
    
}
