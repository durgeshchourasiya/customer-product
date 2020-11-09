package com.assignment.assignmenttask.services;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.assignment.assignmenttask.dto.Customer;
import com.assignment.assignmenttask.exception.ResourceNotFoundException;
import com.assignment.assignmenttask.model.Customers;

public interface CustomerService {
	
	List<Customer> getAllCustomer();
	
	Customer getCustomerById(Long customerId)throws ResourceNotFoundException;
	
	Map<String, Boolean> deleteCustomer(Long customerId)throws ResourceNotFoundException;

	Customer saveCustomer(Customers customer);
	
	Customer updateCustomer(Customer customer, Long customerId) throws ResourceNotFoundException;

	UserDetails loadUserByEmail(String emailId) throws ResourceNotFoundException;
}
