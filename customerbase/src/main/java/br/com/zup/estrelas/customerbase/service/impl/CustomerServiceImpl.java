package br.com.zup.estrelas.customerbase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.customerbase.dto.CustomerDTO;
import br.com.zup.estrelas.customerbase.entities.Customer;
import br.com.zup.estrelas.customerbase.exceptions.BusinessRuleException;
import br.com.zup.estrelas.customerbase.repositories.CustomerRepository;
import br.com.zup.estrelas.customerbase.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private static final String CUSTOMER_NOT_FOUND = "CUSTOMER NOT FOUND.";
	private static final String CUSTUMER_ALREADY_EXISTS = "CUSTUMER ALREADY EXISTS IN THE DATABASE.";
	
	@Autowired
	CustomerRepository repository;
	
	@Override
	public String insert(CustomerDTO customerDTO){
		if (repository.existsByCpf(customerDTO.getCpf())) {
			throw new BusinessRuleException (CUSTUMER_ALREADY_EXISTS);
		}
		return new Customer().create(customerDTO, repository);
	}
	
	@Override
	public List<Customer> findAll() {
		return (List<Customer>) repository.findAll();
	}
	
	@Override
	public Customer find(String cpf) {
		return repository.findByCpf(cpf).orElseThrow(() -> new BusinessRuleException(CUSTOMER_NOT_FOUND));
	}
	
	@Override
	public String updateData(String cpf, CustomerDTO customerDTO) throws BusinessRuleException {
		Customer customer = repository.findByCpf(cpf).orElseThrow(() -> new BusinessRuleException(CUSTOMER_NOT_FOUND));
		
		return customer.update(customerDTO, repository);
	}
	
	@Override
	public void delete(String cpf) {
		Customer customer = repository.findByCpf(cpf).orElseThrow(() -> new BusinessRuleException(CUSTOMER_NOT_FOUND));
		
		repository.delete(customer);
	}

}
