package br.com.zup.estrelas.customerbase.services;

import java.util.List;

import br.com.zup.estrelas.customerbase.dto.CustomerDTO;
import br.com.zup.estrelas.customerbase.entities.Customer;
import br.com.zup.estrelas.customerbase.exceptions.BusinessRuleException;

public interface CustomerService {
	
	public Customer insert (CustomerDTO customerDTO) throws BusinessRuleException;
	
	public List<Customer> findAll();
	
	public Customer find (String cpf) throws BusinessRuleException;
	
	public Customer updateData (String cpf, CustomerDTO customerDTO) throws BusinessRuleException;
	
	public void delete (String cpf) throws BusinessRuleException;

}
