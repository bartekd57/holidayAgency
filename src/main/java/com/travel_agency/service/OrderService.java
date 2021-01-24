package com.travel_agency.service;

import com.travel_agency.dto.OrderoDTO;
import com.travel_agency.dto.ParticipantDTO;
import com.travel_agency.mapper.OrderMapper;
import com.travel_agency.model.order.Ordero;
import com.travel_agency.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderoDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(OrderMapper.INSTANCE::orderDTO)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<OrderoDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper.INSTANCE::orderDTO)
                .collect(Collectors.toList());
    }

    public List<OrderoDTO> getOrderByStatus(String status) {
        return orderRepository.findByStatus(status)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(OrderMapper.INSTANCE::orderDTO)
                .collect(Collectors.toList());
    }

    public List<ParticipantDTO> getParticipantsForOrderById(Long id){
        OrderoDTO orderoDTO = orderRepository.findById(id)
                .map(OrderMapper.INSTANCE::orderDTO)
                .orElseThrow(NoSuchElementException::new);

        return orderoDTO.getParticipants();
    }

    public void saveOrder(OrderoDTO orderoDTO) {
        Ordero ordero = OrderMapper.INSTANCE.dtoToOrder(orderoDTO);
        orderRepository.save(ordero);
    }

    public void deleteOrderById(Long id) {
        Ordero ordero = orderRepository.findById(id).orElseThrow(NoSuchElementException::new);
        orderRepository.delete(ordero);
    }

}
