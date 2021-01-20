package br.com.zup.estrelas.customerbase.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.customerbase.dto.CustomerDTO;
import br.com.zup.estrelas.customerbase.entities.Customer;
import br.com.zup.estrelas.customerbase.exceptions.CustomerAlreadyExistsException;
import br.com.zup.estrelas.customerbase.exceptions.NotFoundException;
import br.com.zup.estrelas.customerbase.repositories.CustomerRepository;
import br.com.zup.estrelas.customerbase.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);
	
	private static final String CUSTOMER_NOT_FOUND = "CUSTOMER NOT FOUND.";
	private static final String CUSTUMER_ALREADY_EXISTS = "CUSTUMER ALREADY EXISTS IN THE DATABASE.";
	
	@Autowired
	CustomerRepository repository;
	
	@Override
	public String insert(CustomerDTO customerDTO){
		if (repository.existsByCpf(customerDTO.getCpf())) {
			throw new CustomerAlreadyExistsException (CUSTUMER_ALREADY_EXISTS);
		}
		return new Customer().create(customerDTO, repository);
	}
	
	@Override
	public List<Customer> findAll() {
		
		List<Customer> customerList = (List<Customer>) repository.findAll();
		
		logger.info("CUSTOMER LIST RETURNED.");
		
		return customerList;
	}
	
	@Override
	public Customer find(String cpf) {
		
		Customer customer = repository.findByCpf(cpf).orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND));
		
		logger.info("CUSTOMER RETURNED.");
		
		return customer;
	}
	
	@Override
	public String updateData(String cpf, CustomerDTO customerDTO){
		Customer customer = repository.findByCpf(cpf).orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND));
		
		return customer.update(customerDTO, repository);
	}
	
	@Override
	public void delete(String cpf) {
		Customer customer = repository.findByCpf(cpf).orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND));
		
		repository.delete(customer);
		
		logger.info("ENTITY DELETED.");
	}

}
