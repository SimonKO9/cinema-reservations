package com.github.simonthecat.cinema.domain.service;

import com.github.simonthecat.cinema.domain.MoviePlayReservation;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {

    MoviePlayReservation placeReservation(Long moviePlayId, String email, int seats);

}
