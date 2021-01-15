package br.com.zup.estrelas.customerbase.services;

import java.util.List;

import br.com.zup.estrelas.customerbase.dto.CustomerDTO;
import br.com.zup.estrelas.customerbase.entities.Customer;

public interface CustomerService {
	
	public String insert (CustomerDTO customerDTO);
	
	public List<Customer> findAll();
	
	public Customer find (String cpf);
	
	public String updateData (String cpf, CustomerDTO customerDTO);
	
	public void delete (String cpf);

}
