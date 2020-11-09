package com.assignment.assignmenttask.controllers;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.assignmenttask.config.JwtTokenUtil;
import com.assignment.assignmenttask.dto.Customer;
import com.assignment.assignmenttask.exception.ResourceNotFoundException;
import com.assignment.assignmenttask.model.Customers;
import com.assignment.assignmenttask.serviceImp.CryptWithMD5;
import com.assignment.assignmenttask.serviceImp.CustomerServiceImplementation;
import com.assignment.assignmenttask.services.AuthenticateService;

@RestController
@RequestMapping("/api/user")
public class CustomerController {

	@Autowired 
	private CustomerServiceImplementation customerService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private AuthenticateService authenticateService;


		@GetMapping(value = { "", "/" })
		public ResponseEntity<List<Customer>> getAllCustomer() {
			return ResponseEntity.ok().body(customerService.getAllCustomer());		
		}

		@GetMapping("/{id}")
		public ResponseEntity<Customer> getEmployeeById(@PathVariable(value = "id") Long customerId)
				throws ResourceNotFoundException {
			Customer customer = customerService.getCustomerById(customerId);
			return ResponseEntity.ok().body(customer);
		}

		@PostMapping("/add")
		public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customers customer) {
			Customer customerDetails = customerService.saveCustomer(customer);
			return ResponseEntity.ok().body(customerDetails);
		}

		@PutMapping("/update/{id}")
		public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
				@Valid @RequestBody Customer userDetails) throws ResourceNotFoundException {
			final Customer updatedUser = customerService.updateCustomer(userDetails, customerId);
			return ResponseEntity.ok(updatedUser);
		}

		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable(value = "id") Long customerId)
				throws ResourceNotFoundException {		
			return ResponseEntity.ok(customerService.deleteCustomer(customerId));
		}
		
		@PostMapping("/login")
		public ResponseEntity<?> generateAuthenticationToken(@RequestBody Map<String, String> credential)
				throws Exception {
			String pass = CryptWithMD5.cryptWithMD5(credential.get("password"));
			authenticateService.authenticate(credential.get("emailId"), pass);

			final UserDetails userDetails = customerService.loadUserByEmail(credential.get("emailId"));
			final String token = jwtTokenUtil.generateToken(userDetails);
			return ResponseEntity.ok(token);
		}
		
		 @GetMapping(path = "/logout")
		    public ResponseEntity<?> logout(@RequestHeader(value="Authorization") String authToken) {
			 System.out.println(authToken);
			 jwtTokenUtil.canTokenBeRefreshed(authToken);
			 return ResponseEntity.ok("done");
		    }
	}