package com.assignment.assignmenttask.serviceImp;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.assignment.assignmenttask.dto.OrderProductDto;
import com.assignment.assignmenttask.model.Order;
import com.assignment.assignmenttask.repository.OrderRepository;
import com.assignment.assignmenttask.services.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
 
	OrderRepository orderRepository;
 
    @Override
    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }
	
    @Override
    public Order create(Order order) {
        order.setDateCreated(LocalDate.now());
        return this.orderRepository.save(order);
    }
 
    @Override
    public void update(Order order) {
        this.orderRepository.save(order);
    }
  
}