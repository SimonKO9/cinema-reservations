package com.github.simonthecat.cinema.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cinemahalls")
public class CinemaHall {

    @Id
    @Column(name = "key")
    private String key;

    @Column(nullable = false)
    private int seats;

    public String getKey() {
        return key;
    }

    public int getSeats() {
        return seats;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
