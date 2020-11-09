package com.assignment.assignmenttask.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.assignmenttask.dto.Customer;
import com.assignment.assignmenttask.dto.OrderProductDto;
import com.assignment.assignmenttask.model.Customers;
import com.assignment.assignmenttask.model.Product;
import com.assignment.assignmenttask.serviceImp.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductController {
 
	@Autowired
	ProductServiceImpl productServiceImpl;
 
    @GetMapping(value = { "", "/" })
    public @NotNull Iterable<Product> getProducts() {
        return productServiceImpl.getAllProducts();
    }

	@PostMapping("/add")
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
		Product customerDetails = productServiceImpl.save(product);
		return ResponseEntity.ok().body(customerDetails);
	}
	 

}