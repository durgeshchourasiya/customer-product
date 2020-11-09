package com.assignment.assignmenttask.helper;



import com.assignment.assignmenttask.dto.Customer;
import com.assignment.assignmenttask.model.Customers;

public class CustomerParse {
	
	public static Customer getCustomer(Customers customer) {
		return new Customer()
		.setId(customer.getId())
		.setName(customer.getName())
		.setEmail(customer.getEmail())
		.setAddress(customer.getAddress())
		.setContactNo(customer.getContactNo());
	}

}
