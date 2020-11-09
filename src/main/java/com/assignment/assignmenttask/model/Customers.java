package com.assignment.assignmenttask.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "customer")
public class Customers {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column(unique=true)
	@NotEmpty(message = "{constraints.NotEmpty.Email.message}")
    @Email(message = "{constraints.Email.message}")
    @Pattern(regexp = "^.+@.+\\..+$", message = "{constraints.Email.message}")
	private String email;
	@Column
	private Long contactNo;
	@Column
	private String address;
	@Column
	private String password;
	

}
