package com.assignment.assignmenttask.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.assignment.assignmenttask.Enums.OrderStatus;
import com.assignment.assignmenttask.dto.OrderForm;
import com.assignment.assignmenttask.dto.OrderProductDto;
import com.assignment.assignmenttask.exception.ProductNotFountException;
import com.assignment.assignmenttask.model.Order;
import com.assignment.assignmenttask.model.OrderProduct;
import com.assignment.assignmenttask.serviceImp.OrderServiceImpl;
import com.assignment.assignmenttask.serviceImp.ProductServiceImpl;


@RestController
@RequestMapping("/api/order")
public class OrderController {
	@Autowired
	OrderServiceImpl orderService;
	@Autowired
	ProductServiceImpl productService;
	
	@PostMapping("/add")
	public ResponseEntity<Order> create(@RequestBody OrderForm form) throws ProductNotFountException {
	    List<OrderProductDto> formDtos = form.getProductOrders();
	    List<OrderProduct> orderProducts =  productService.validateProductsExistence(formDtos);
	    System.out.println("-------"+orderProducts);
	    Order order = new Order();
	    order.setStatus(OrderStatus.PENDING);
	    order.setDateCreated(LocalDate.now());
	    order.setOrderProducts(orderProducts);
	    orderService.update(order);
	 
	    String uri = ServletUriComponentsBuilder
	      .fromCurrentServletMapping()
	      .path("/orders/{id}")
	      .buildAndExpand(order.getId())
	      .toString();
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Location", uri);
	 
	    return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
	}
}
