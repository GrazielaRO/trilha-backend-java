package br.com.zup.estrelas.customerbase.service.impl;

import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.zup.estrelas.customerbase.dto.CustomerDTO;
import br.com.zup.estrelas.customerbase.entities.Customer;
import br.com.zup.estrelas.customerbase.exceptions.CustomerAlreadyExistsException;
import br.com.zup.estrelas.customerbase.exceptions.NotFoundException;
import br.com.zup.estrelas.customerbase.repositories.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

	@Mock
	CustomerRepository repository;

	@InjectMocks
	CustomerServiceImpl service;

	@Test
	public void shouldInsertCustomer() {

		CustomerDTO customerDto = customerDTOFactory();

		Mockito.when(repository.existsByCpf(customerDto.getCpf())).thenReturn(false);

		Customer customer = customerFactory(customerDto);

		Mockito.when(repository.save(any())).thenReturn(customer);

		Assert.assertEquals(service.insert(customerDto), customer.getId());

	}

	@Test(expected = CustomerAlreadyExistsException.class)
	public void shouldNotInsertCustomer() {

		CustomerDTO customerDto = customerDTOFactory();

		Mockito.when(repository.existsByCpf(customerDto.getCpf())).thenReturn(true);

		service.insert(customerDto);

	}
	
	@Test
	public void shouldFindAllCustomers() {
		
		List<Customer> customerList = customerListFactory();
		
		Mockito.when(repository.findAll()).thenReturn(customerList);
		
		Assert.assertEquals(customerList, service.findAll());

	}

	@Test
	public void shouldFindCustomerByCpf() {

		CustomerDTO customerDto = customerDTOFactory();

		Customer customer = customerFactory(customerDto);

		Mockito.when(repository.findByCpf(customer.getCpf())).thenReturn(Optional.of(customer));

		Assert.assertEquals(customer, service.find(customer.getCpf()));
	}
	
	@Test(expected = NotFoundException.class)
	public void shouldNotFindCustomerByCpf() {

		CustomerDTO customerDto = customerDTOFactory();

		Mockito.when(repository.findByCpf(customerDto.getCpf())).thenReturn(Optional.empty());
		
		service.find(customerDto.getCpf());

	}
	
	@Test
	public void shouldUpdateCustomer() {
		
		CustomerDTO customerDto = customerDTOFactory();

		Customer customer = customerFactory(customerDto);
		
		Mockito.when(repository.findByCpf(customer.getCpf())).thenReturn(Optional.of(customer));
		
		Mockito.when(repository.save(any())).thenReturn(customer);
		
		Assert.assertEquals(service.updateData(customer.getCpf(), customerDto), customer.getId());
	}
	
	@Test(expected = NotFoundException.class)
	public void shouldNotUpdateCustomer() {
		
		CustomerDTO customerDto = customerDTOFactory();

		Customer customer = customerFactory(customerDto);
		
		Mockito.when(repository.findByCpf(customer.getCpf())).thenReturn(Optional.empty());
		
		service.updateData(customer.getCpf(), customerDto);
		
	}
	
	@Test
	public void shouldDeleteCustomer() {
		
		CustomerDTO customerDto = customerDTOFactory();

		Customer customer = customerFactory(customerDto);
		
		Mockito.when(repository.findByCpf("79885268049")).thenReturn(Optional.of(customer));
		
		service.delete("79885268049");
		
		Mockito.verify(repository).delete(customer);
	}
	
	@Test(expected = NotFoundException.class)
	public void shouldNotDeleteCustomer() {
		
		CustomerDTO customerDto = customerDTOFactory();

		Customer customer = customerFactory(customerDto);
		
		Mockito.when(repository.findByCpf(customer.getCpf())).thenReturn(Optional.empty());
		
		service.delete(customer.getCpf());
		
	}

	private CustomerDTO customerDTOFactory() {

		CustomerDTO customerDto = new CustomerDTO("Maria Luiza", Date.valueOf("1951-10-29"), "79885268049",
				"maria@mail.com", "34982526431", "Rua da felicidade, 100");

		return customerDto;
	}

	private Customer customerFactory(CustomerDTO customerDto) {

		Customer customer = new Customer(UUID.randomUUID().toString(), customerDto.getName(),
				customerDto.getBirthDate(), customerDto.getCpf(), customerDto.getEmail(), customerDto.getPhoneNumber(),
				customerDto.getAddress());

		return customer;
	}
	
	private List<Customer> customerListFactory(){
		
		List<Customer> customerList = new ArrayList<>();
		
		Customer customerOne = new Customer (UUID.randomUUID().toString(), "Graziela Oliveira",
				Date.valueOf("1984-10-21"), "33852937078", "grazi@mail.com", "34987654136","Rua da Vitória, 100");
		
		Customer customerTwo = new Customer (UUID.randomUUID().toString(), "José Francisco",
				Date.valueOf("1955-07-31"), "63139533047", "jose@mail.com", "34983567495","Rua da Alegria, 100");
		
		customerList.add(customerOne);
		customerList.add(customerTwo);
		
		return customerList;
	}

}
