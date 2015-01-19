package com.github.simonthecat.cinema.domain.persistence;

import com.github.simonthecat.cinema.domain.MoviePlayReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import com.github.simonthecat.cinema.domain.MoviePlay;

import java.util.List;

public interface MoviePlayReservationRepository extends JpaRepository<MoviePlayReservation, String> {

    List<MoviePlayReservation> findByMoviePlay(MoviePlay moviePlay);

}
