package br.com.zup.estrelas.customerbase.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.zup.estrelas.customerbase.dto.CustomerDTO;
import br.com.zup.estrelas.customerbase.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "customer")
public class Customer {
	
	private static final Logger logger = LogManager.getLogger(Customer.class);
	
	@Column(unique = true, nullable = false)
	@Id
	private String id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(name = "birth_date", nullable = false)
	private Date birthDate;
	
	@Column(nullable = false, unique = true)
	private String cpf;
	
	@Column(nullable = false)
	private String email;
	
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(nullable = false)
	private String address;
	
	public String create (CustomerDTO customerDTO, CustomerRepository repository) {
		this.id = UUID.randomUUID().toString();
		this.name = customerDTO.getName();
		this.birthDate = customerDTO.getBirthDate();
		this.cpf = customerDTO.getCpf();
		this.email = customerDTO.getEmail();
		this.phoneNumber = customerDTO.getPhoneNumber();
		this.address = customerDTO.getAddress();
	
		String entity = repository.save(this).id;
		
		logger.info("ENTITY CREATED.");
		
		return entity;
	}
	
	public String update (CustomerDTO customerDTO, CustomerRepository repository) {
		this.name = customerDTO.getName();
		this.birthDate = customerDTO.getBirthDate();
		this.cpf = customerDTO.getCpf();
		this.email = customerDTO.getEmail();
		this.phoneNumber = customerDTO.getPhoneNumber();
		this.address = customerDTO.getAddress();
		
		String entityUpdated = repository.save(this).id;
		
		logger.info("ENTITY UPDATED.");
		
		return entityUpdated;
	}
	
}
