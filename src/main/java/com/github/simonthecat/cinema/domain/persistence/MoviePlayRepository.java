package com.github.simonthecat.cinema.domain.persistence;

import com.github.simonthecat.cinema.domain.MoviePlay;
import org.springframework.data.jpa.repository.JpaRepository;
import com.github.simonthecat.cinema.domain.Movie;

import java.time.LocalDateTime;
import java.util.List;

public interface MoviePlayRepository extends JpaRepository<MoviePlay, Long> {

    List<MoviePlay> findByMovieAndPlayDateBetween(Movie movie, LocalDateTime fromDate, LocalDateTime toDate);

}
