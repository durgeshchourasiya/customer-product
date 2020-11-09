package com.assignment.assignmenttask.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class OrderProductDto {
	 private int quantity;
	 private Long productId;
	 
	 

}
