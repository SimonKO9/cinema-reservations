package com.github.simonthecat.cinema.http.rs;

import com.github.simonthecat.cinema.domain.MoviePlayReservation;
import com.github.simonthecat.cinema.domain.service.MoviePlayService;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.criterion.Restrictions.*;
import static org.springframework.util.StringUtils.isEmpty;


@RestController
@Transactional
public class ReservationResource {

    @Autowired
    MoviePlayService moviePlayService;

    @RequestMapping(value = "/api/reservations")
    @SuppressWarnings("unchecked")
    public List<MoviePlayReservation> find(@ModelAttribute SearchReservationParameters parameters) {
        return moviePlayService.findReservations(parameters);
    }

}
