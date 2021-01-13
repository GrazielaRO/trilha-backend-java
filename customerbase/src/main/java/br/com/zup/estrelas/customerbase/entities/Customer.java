package br.com.zup.estrelas.customerbase.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.zup.estrelas.customerbase.dto.CustomerDTO;
import br.com.zup.estrelas.customerbase.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
	
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
		
		return repository.save(this).id;
	}
	
	public String update (CustomerDTO customerDTO, CustomerRepository repository) {
		this.name = customerDTO.getName();
		this.birthDate = customerDTO.getBirthDate();
		this.cpf = customerDTO.getCpf();
		this.email = customerDTO.getEmail();
		this.phoneNumber = customerDTO.getPhoneNumber();
		this.address = customerDTO.getAddress();
		
		return repository.save(this).id;
	}
	
}
