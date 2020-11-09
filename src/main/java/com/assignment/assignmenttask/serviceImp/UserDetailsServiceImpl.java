package com.assignment.assignmenttask.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.assignmenttask.model.Customers;
import com.assignment.assignmenttask.repository.CustomerRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository usersRepository;
	
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Customers> optionalUser = usersRepository.findByEmail(userName);
        if(optionalUser.isPresent()) {
        	Customers users = optionalUser.get();
        	
        	System.out.println(users);
            return User.builder()
            	.username(users.getEmail())
            	.password( bCryptPasswordEncoder.encode(users.getPassword()))
            	.roles("USER")
            	.build();
        } else {
        	throw new UsernameNotFoundException("User Name is not Found");
        }   
    }
}