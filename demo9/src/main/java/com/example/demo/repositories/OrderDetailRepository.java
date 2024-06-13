package com.example.demo.repositories;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<Order, Long> {
}
