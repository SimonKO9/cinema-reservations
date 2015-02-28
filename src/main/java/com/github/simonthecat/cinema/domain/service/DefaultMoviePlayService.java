package com.github.simonthecat.cinema.domain.service;

import com.github.simonthecat.cinema.domain.MoviePlayReservation;
import com.github.simonthecat.cinema.http.rs.SearchReservationParameters;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.criterion.Restrictions.*;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class DefaultMoviePlayService implements MoviePlayService {

    private final HibernateTemplate hibernateTemplate;

    @Autowired
    public DefaultMoviePlayService(EntityManagerFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory.unwrap(SessionFactory.class));
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<MoviePlayReservation> findReservations(SearchReservationParameters parameters) {
        List<Criterion> criteria = toCriteria(parameters);
        DetachedCriteria queryCriteria = DetachedCriteria.forClass(MoviePlayReservation.class);
        queryCriteria.add(and(criteria.toArray(new Criterion[criteria.size()])));

        return (List<MoviePlayReservation>) hibernateTemplate.findByCriteria(queryCriteria);
    }

    private List<Criterion> toCriteria(SearchReservationParameters parameters) {
        List<Criterion> criteria = new ArrayList<>();
        if (!isEmpty(parameters.getReservationId())) {
            criteria.add(ilike("reservationNumber", parameters.getReservationId(), MatchMode.ANYWHERE));
        }
        if (!isEmpty(parameters.getEmail())) {
            criteria.add(ilike("email", parameters.getEmail(), MatchMode.ANYWHERE));
        }
        if (!isEmpty(parameters.getMovieTitle())) {
            criteria.add(ilike("moviePlay.movie.title", parameters.getMovieTitle(), MatchMode.ANYWHERE));
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
