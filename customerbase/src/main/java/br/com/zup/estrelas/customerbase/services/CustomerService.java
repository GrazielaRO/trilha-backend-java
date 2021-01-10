package br.com.zup.estrelas.customerbase.services;

import java.util.List;

import br.com.zup.estrelas.customerbase.dto.UpdateCustomerDataDTO;
import br.com.zup.estrelas.customerbase.dto.InsertCustomerDTO;
import br.com.zup.estrelas.customerbase.entities.Customer;
import br.com.zup.estrelas.customerbase.exceptions.BusinessRuleException;

public interface CustomerService {
	
	public Customer insert (InsertCustomerDTO customerDTO) throws BusinessRuleException;
	
	public List<Customer> findAll();
	
	public Customer find (String cpf) throws BusinessRuleException;
	
	public Customer updateData (String cpf, UpdateCustomerDataDTO updateCustomerDataDTO) throws BusinessRuleException;
	
	public void delete (String cpf) throws BusinessRuleException;

}
