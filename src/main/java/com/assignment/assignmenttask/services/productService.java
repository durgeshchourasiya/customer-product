package com.assignment.assignmenttask.services;

import java.util.List;

import com.assignment.assignmenttask.dto.OrderProductDto;
import com.assignment.assignmenttask.exception.ProductNotFountException;
import com.assignment.assignmenttask.exception.ResourceNotFoundException;
import com.assignment.assignmenttask.model.OrderProduct;
import com.assignment.assignmenttask.model.Product;

public interface productService {
	
	public Iterable<Product> getAllProducts();
	
	public Product getProduct(long id)  throws ResourceNotFoundException;
	
	public Product save(Product product);
	
	public List<OrderProduct> validateProductsExistence(List<OrderProductDto> formDtos)throws ProductNotFountException;
}
