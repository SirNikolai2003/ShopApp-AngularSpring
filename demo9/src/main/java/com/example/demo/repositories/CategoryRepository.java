package com.example.demo.repositories;

import com.example.demo.entity.Category;
import com.example.demo.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
//    List<OrderDetail> findByOrderId(Long orderId);
}
