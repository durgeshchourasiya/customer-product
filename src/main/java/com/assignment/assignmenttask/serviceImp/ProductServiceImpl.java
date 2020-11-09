package com.assignment.assignmenttask.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.assignmenttask.dto.OrderProductDto;
import com.assignment.assignmenttask.exception.ProductNotFountException;
import com.assignment.assignmenttask.exception.ResourceNotFoundException;
import com.assignment.assignmenttask.helper.ProductOrderMapper;
import com.assignment.assignmenttask.model.OrderProduct;
import com.assignment.assignmenttask.model.Product;
import com.assignment.assignmenttask.repository.ProductRepository;
import com.assignment.assignmenttask.services.productService;

@Service
@Transactional
public class ProductServiceImpl implements productService {
 
	@Autowired
	ProductRepository productRepository;
 
    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
 
    @Override
    public Product getProduct(long id) throws ResourceNotFoundException {
        return productRepository
          .findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
 
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    @Override
    public List<OrderProduct> validateProductsExistence(List<OrderProductDto> formDtos) throws ProductNotFountException {
    	List<Product> productList = new ArrayList<Product>();
    	List<OrderProduct> orderProductList = new ArrayList();

    	for(OrderProductDto opdto:formDtos) {

    		Optional<Product> product = productRepository.findById(opdto.getProductId());
        	
    			if(product.isPresent()) {
    				productList.add(product.get());
    				orderProductList.add(ProductOrderMapper.getProductOrder(product.get(), opdto.getQuantity()));
    			}else {
    				throw new ProductNotFountException();
    			}
    		
    	}
   
    	return orderProductList;
}
}