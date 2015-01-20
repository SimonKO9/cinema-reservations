package com.github.simonthecat.cinema.http.rs;

import com.github.simonthecat.cinema.domain.MoviePlay;
import com.github.simonthecat.cinema.domain.MoviePlayReservation;
import com.github.simonthecat.cinema.domain.persistence.MoviePlayRepository;
import com.github.simonthecat.cinema.domain.persistence.MoviePlayReservationRepository;
import com.github.simonthecat.cinema.http.dto.MoviePlayDto;
import com.github.simonthecat.cinema.http.dto.MoviePlayReservationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.github.simonthecat.cinema.domain.Movie;
import com.github.simonthecat.cinema.domain.persistence.MovieRepository;
import com.github.simonthecat.cinema.domain.service.ReservationService;
import com.github.simonthecat.cinema.http.dto.MoviePlayReservationDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.github.simonthecat.cinema.http.dto.MoviePlayReservationDto.fromMoviePlayReservation;
import static java.util.stream.Collectors.toList;

@Controller
public class MoviePlayResource {

    private MoviePlayRepository moviePlayRepository;

    private MovieRepository movieRepository;

    private MoviePlayReservationRepository moviePlayReservationRepository;

    private ReservationService reservationService;

    @Autowired
    public MoviePlayResource(MoviePlayRepository moviePlayRepository, MovieRepository movieRepository, MoviePlayReservationRepository moviePlayReservationRepository, ReservationService reservationService) {
        this.moviePlayRepository = moviePlayRepository;
        this.movieRepository = movieRepository;
        this.moviePlayReservationRepository = moviePlayReservationRepository;
        this.reservationService = reservationService;
    }

    @RequestMapping(value = "/api/movies/{movieId}/plays", method = RequestMethod.GET)
    @ResponseBody
    public List<MoviePlayDto> getMoviePlays(@PathVariable("movieId") Long movieId) {
        Movie movie = movieRepository.findOne(movieId);
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime todayEnd = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);

        return moviePlayRepository.findByMovieAndPlayDateBetween(movie, todayStart, todayEnd).stream()
                .map(MoviePlayDto::fromMoviePlay)
                .collect(toList());
    }

    @RequestMapping(value = "/api/movies/{movieId}/plays/{moviePlayId}", method = RequestMethod.GET)
    @ResponseBody
    public MoviePlayDto getMoviePlay(@PathVariable("movieId") Long movieId, @PathVariable("moviePlayId") Long moviePlayId) {
        MoviePlay play = moviePlayRepository.findOne(moviePlayId);
        return MoviePlayDto.fromMoviePlay(play);
    }

    @RequestMapping(value = "/api/movies/{movieId}/plays/{moviePlayId}/reservations", method = RequestMethod.GET)
    @ResponseBody
    public List<MoviePlayReservationDto> getReservations(@PathVariable("moviePlayId") Long moviePlayId) {
        MoviePlay moviePlay = moviePlayRepository.findOne(moviePlayId);

        return moviePlayReservationRepository.findByMoviePlay(moviePlay).stream()
                .map(MoviePlayReservationDto::fromMoviePlayReservation)
                .collect(toList());
    }

    @RequestMapping(value = "/api/movies/{movieId}/plays/{moviePlayId}/reservations", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public MoviePlayReservationDto placeReservation(@PathVariable Long moviePlayId, @RequestBody MoviePlayReservationCommand reservationCmd) {
        String email = reservationCmd.getEmail();
        int seatsTaken = reservationCmd.getSeatsTaken();
        MoviePlayReservation reservation = reservationService.placeReservation(moviePlayId, email, seatsTaken);
        return fromMoviePlayReservation(reservation);
    }

}
