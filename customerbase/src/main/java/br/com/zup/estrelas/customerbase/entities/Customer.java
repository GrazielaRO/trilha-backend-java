package br.com.zup.estrelas.customerbase.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
	
	@Column(nullable = false)
	private String name;
	
	@Column(name = "birth_date", nullable = false)
	private Date birthDate;
	
	@Column(nullable = false, unique = true)
	@Id
	private String cpf;
	
	@Column(nullable = false)
	private String email;
	
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(nullable = false)
	private String address;
	
}
