package br.com.zup.estrelas.customerbase.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsertCustomerDTO {
	
	private String name;
	
	private Date birthDate;
	
	private String cpf;
	
	private String email;
	
	private String phoneNumber;
	
	private String address;
}
