package com.github.simonthecat.cinema.domain.persistence;

import com.github.simonthecat.cinema.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
