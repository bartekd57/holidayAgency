package com.travel_agency.model.order;

import com.travel_agency.model.participant.Participant;
import com.travel_agency.model.trip.Trip;
import com.travel_agency.model.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ordero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ordero", cascade = CascadeType.ALL)
    List<Participant> participants = new ArrayList<>();
    @OneToOne
    private User user;
    @OneToOne
    private Trip trip;

    public Ordero(Long id, BigDecimal balance, OrderStatusEnum status, List<Participant> participants, User user, Trip trip) {
        this.id = id;
        this.balance = balance;
        this.status = status;
        this.participants = participants;
        this.user = user;
        this.trip = trip;
    }

    public Ordero() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
