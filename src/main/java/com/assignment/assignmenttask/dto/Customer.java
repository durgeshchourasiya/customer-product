package com.assignment.assignmenttask.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
public class Customer {
	private Long id;
	private String name;
	@NotEmpty(message = "{constraints.NotEmpty.Email.message}")
    @Email(message = "{constraints.Email.message}")
    @Pattern(regexp = "^.+@.+\\..+$", message = "{constraints.Email.message}")
	private String email;
	private Long contactNo;
	private String address;
}
