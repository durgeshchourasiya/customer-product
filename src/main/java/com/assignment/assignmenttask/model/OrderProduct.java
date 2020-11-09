package com.assignment.assignmenttask.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.boot.context.properties.ConstructorBinding;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class OrderProduct {
 
    @EmbeddedId
    @JsonIgnore
    private OrderProductPK pk;
 
    @Column(nullable = false)
	private Integer quantity;
 
    public OrderProduct(Order order, Product product, Integer quantity) {
        pk = new OrderProductPK();
        pk.setOrder(order);
        pk.setProduct(product);
        this.quantity = quantity;
    }
 
    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }
 
    @Transient
    public Double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }

}