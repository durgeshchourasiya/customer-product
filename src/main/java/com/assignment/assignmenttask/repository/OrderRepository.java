package com.assignment.assignmenttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.assignmenttask.model.Order;
import com.assignment.assignmenttask.model.Product;

public interface OrderRepository  extends JpaRepository<Order, Long>{
}
