package com.travel_agency.dto;

import com.travel_agency.model.order.OrderStatusEnum;
import com.travel_agency.security.DTO.UserDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class OrderoDTO {
    private Long id;
    private TripDTO tripDTO;
    private List<ParticipantDTO> participants = new ArrayList<>();
    private BigDecimal balance;
    private UserDTO userDTO;
    private OrderStatusEnum orderStatusEnum;

    public OrderoDTO(Long id, TripDTO tripDTO, List<ParticipantDTO> participants, BigDecimal balance, UserDTO userDTO, OrderStatusEnum orderStatusEnum) {
        this.id = id;
        this.tripDTO = tripDTO;
        this.participants = participants;
        this.balance = balance;
        this.userDTO = userDTO;
        this.orderStatusEnum = orderStatusEnum;
    }

    public OrderoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TripDTO getTripDTO() {
        return tripDTO;
    }

    public void setTripDTO(TripDTO tripDTO) {
        this.tripDTO = tripDTO;
    }

    public List<ParticipantDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantDTO> participants) {
        this.participants = participants;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public OrderStatusEnum getOrderStatusEnum() {
        return orderStatusEnum;
    }

    public void setOrderStatusEnum(OrderStatusEnum orderStatusEnum) {
        this.orderStatusEnum = orderStatusEnum;
    }
}
