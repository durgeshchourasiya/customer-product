package com.assignment.assignmenttask.services;

import com.assignment.assignmenttask.model.Order;

public interface OrderService {

	 public Iterable<Order> getAllOrders();
	 
	 public Order create(Order order);
	 
	 public void update(Order order);
	 
}
