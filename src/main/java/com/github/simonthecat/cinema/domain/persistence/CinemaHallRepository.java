package com.github.simonthecat.cinema.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.simonthecat.cinema.domain.CinemaHall;

public interface CinemaHallRepository extends JpaRepository<CinemaHall, String> {
}
