package com.github.simonthecat.cinema.http.rs;

import com.github.simonthecat.cinema.domain.MoviePlayReservation;
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

    private final HibernateTemplate hibernateTemplate;

    @Autowired
    public ReservationResource(EntityManagerFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory.unwrap(SessionFactory.class));
    }

    @RequestMapping(value = "/api/reservations")
    @SuppressWarnings("unchecked")
    public List<MoviePlayReservation> find(@ModelAttribute SearchReservationParameters parameters) {
        List<Criterion> criteria = toCriteria(parameters);
        DetachedCriteria queryCriteria = DetachedCriteria.forClass(MoviePlayReservation.class);
        queryCriteria.add(and(criteria.toArray(new Criterion[criteria.size()])));

        return (List<MoviePlayReservation>) hibernateTemplate.findByCriteria(queryCriteria);
    }

    private List<Criterion> toCriteria(SearchReservationParameters parameters) {
        List<Criterion> criteria = new ArrayList<>();
        if (!isEmpty(parameters.getReservationId())) {
            criteria.add(eq("reservationNumber", parameters.getReservationId()));
        }
        if (!isEmpty(parameters.getEmail())) {
            criteria.add(eq("email", parameters.getEmail()));
        }
        if (!isEmpty(parameters.getMovieTitle())) {
            criteria.add(eq("moviePlay.movie.title", parameters.getMovieTitle()));
        }
        if (parameters.getDateFrom() != null) {
            criteria.add(ge("moviePlay.playDate", parameters.getDateFrom()));
        }
        if (parameters.getDateTo() != null) {
            criteria.add(le("moviePlay.playDate", parameters.getDateTo()));
        }
        return criteria;
    }

}
