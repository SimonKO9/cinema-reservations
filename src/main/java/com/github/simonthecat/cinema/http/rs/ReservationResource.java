package com.github.simonthecat.cinema.http.rs;

import com.github.simonthecat.cinema.domain.MoviePlayReservation;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReservationResource {

    @RequestMapping(value = "/api/reservations")
    public List<MoviePlayReservation> find(@ModelAttribute MoviePlayReservationParameters parameters) {
        // TODO
        return new ArrayList<>();
    }

}
