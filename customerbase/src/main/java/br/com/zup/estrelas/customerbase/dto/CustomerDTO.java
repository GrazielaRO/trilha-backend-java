package br.com.zup.estrelas.customerbase.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;

@Getter
public class CustomerDTO {
	
	@NotBlank(message = "{validation.name.blank}")
	private String name;
	
	@Past(message = "{validation.invalid.date}")
	private Date birthDate;
	
	@CPF(message = "{validation.invalid.cpf}")
	private String cpf;
	
	@NotEmpty(message = "{validation.email.notempty}")
	@Email(message = "{validation.invalid.email}")
	private String email;
	
	@NotNull(message= "{validation.phone.notnull}")
	@Size(min = 10, max = 11, message = "{validation.phone}")
	private String phoneNumber;
	
	@NotBlank(message = "{validation.address.blank}")
	private String address;
}
