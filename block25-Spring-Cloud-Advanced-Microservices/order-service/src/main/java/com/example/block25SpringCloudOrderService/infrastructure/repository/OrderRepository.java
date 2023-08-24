package com.example.block25SpringCloudOrderService.infrastructure.repository;

import com.example.block25SpringCloudOrderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
