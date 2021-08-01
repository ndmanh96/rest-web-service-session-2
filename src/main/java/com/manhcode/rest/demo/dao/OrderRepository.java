package com.manhcode.rest.demo.dao;

import com.manhcode.rest.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
