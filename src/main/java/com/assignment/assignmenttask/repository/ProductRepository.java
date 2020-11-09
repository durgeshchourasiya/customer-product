package com.assignment.assignmenttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.assignmenttask.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
