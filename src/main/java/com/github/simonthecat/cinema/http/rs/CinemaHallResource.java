package com.github.simonthecat.cinema.http.rs;

import com.github.simonthecat.cinema.domain.persistence.CinemaHallRepository;
import com.github.simonthecat.cinema.http.dto.CinemaHallDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
public class CinemaHallResource {

    private CinemaHallRepository cinemaHallRepository;

    @Autowired
    public CinemaHallResource(CinemaHallRepository cinemaHallRepository) {
        this.cinemaHallRepository = cinemaHallRepository;
    }

    @RequestMapping("/api/cinemaHalls")
    @ResponseBody
    public List<CinemaHallDto> getAll() {
        return cinemaHallRepository.findAll().stream()
                .map(CinemaHallDto::fromDto)
                .collect(toList());
    }

}
