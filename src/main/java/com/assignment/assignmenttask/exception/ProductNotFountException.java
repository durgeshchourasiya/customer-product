package com.assignment.assignmenttask.exception;

public class ProductNotFountException extends Exception{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFountException(){  
		  super("Product is out of stock");  
		 } 
}
