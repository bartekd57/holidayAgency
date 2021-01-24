package com.travel_agency.repository;

import com.travel_agency.model.order.Ordero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Ordero,Long> {
    Optional<List<Ordero>> findByStatus(String status);
}
