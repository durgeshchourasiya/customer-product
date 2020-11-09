package com.assignment.assignmenttask.helper;

import java.time.LocalDate;

import com.assignment.assignmenttask.Enums.OrderStatus;
import com.assignment.assignmenttask.model.Order;
import com.assignment.assignmenttask.model.OrderProduct;
import com.assignment.assignmenttask.model.OrderProductPK;
import com.assignment.assignmenttask.model.Product;

public class ProductOrderMapper {
	
	public static OrderProduct getProductOrder(Product product, Integer quantity) {
		OrderProductPK orderProductPK = new OrderProductPK();
				orderProductPK.setProduct(product);
	
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setPk(orderProductPK);
		orderProduct.setQuantity(quantity);
		return orderProduct;
	
	}
}
