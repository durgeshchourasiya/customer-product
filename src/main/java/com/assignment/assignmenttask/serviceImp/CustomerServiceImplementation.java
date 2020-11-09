package com.assignment.assignmenttask.serviceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.assignment.assignmenttask.dto.Customer;
import com.assignment.assignmenttask.exception.ResourceNotFoundException;
import com.assignment.assignmenttask.helper.CustomerParse;
import com.assignment.assignmenttask.model.Customers;
import com.assignment.assignmenttask.repository.CustomerRepository;
import com.assignment.assignmenttask.services.CustomerService;


@Service
public class CustomerServiceImplementation implements CustomerService {
	@Autowired
	private CustomerRepository customerRepo;	

	@Override
	@Transactional
	public Customer saveCustomer(Customers customer) {
		
		customer.setPassword(CryptWithMD5.cryptWithMD5(customer.getPassword()));
		Customers customerDto = customerRepo.save(customer);
		Customer customerDetails = CustomerParse.getCustomer(customerDto);		
		return customerDetails;
	}

	@Override
	@Transactional
	public Customer updateCustomer(Customer customer, Long customerId) throws ResourceNotFoundException {
		Customers customers= customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

		customers.setEmail(customer.getEmail());
		customers.setName(customer.getName());
		customers.setAddress(customer.getAddress());
		customers.setContactNo(customer.getContactNo());
		final Customers updatedCustomer = customerRepo.save(customers);
		Customer updatedUser = CustomerParse.getCustomer(updatedCustomer);		
		return updatedUser;
	}

	@Override
	@Transactional
	public List<Customer> getAllCustomer() {
		
		 List<Customers> customerDtoList = customerRepo.findAll();
		 List<Customer> customerList = customerDtoList.stream()
				 .map(obj->CustomerParse.getCustomer(obj))
				 .collect(Collectors.toList());
		 return customerList;
	}

	@Override
	@Transactional
	public Customer getCustomerById(Long customerId) throws ResourceNotFoundException {
		Customers customerDto = customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		Customer customer = CustomerParse.getCustomer(customerDto);
		return customer;
	}

	@Override
	@Transactional
	public Map<String, Boolean> deleteCustomer(Long customerId) throws ResourceNotFoundException {
		Customers customerDto = customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + customerId));

		customerRepo.delete(customerDto);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@Override
	@Transactional
	public UserDetails loadUserByEmail(String emailId) throws ResourceNotFoundException {
		Optional<Customers> customer = customerRepo.findByEmail(emailId);
		if(customer.isPresent()) {
			return new User(customer.get().getEmail(), customer.get().getPassword(), new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User not found with username: " + emailId);
		}
	}
	
}
