package com.github.simonthecat.cinema.http.rs;

import com.github.simonthecat.cinema.domain.Movie;
import com.github.simonthecat.cinema.domain.persistence.MovieRepository;
import com.github.simonthecat.cinema.http.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Controller
public class MovieResource {

    private MovieRepository movieRepository;

    @Autowired
    public MovieResource(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RequestMapping("/api/movies/{id}")
    @ResponseBody
    public HttpEntity get(@PathVariable("id") Long id) {
        Movie movie = movieRepository.findOne(id);
        if(movie == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new MovieDto(movie), HttpStatus.OK);
        }
    }

    @RequestMapping("/api/movies")
    @ResponseBody
    public List<MovieDto> getAll() {
        return movieRepository.findAll().stream()
                .map(MovieDto::new)
                .collect(toList());
    }

}
