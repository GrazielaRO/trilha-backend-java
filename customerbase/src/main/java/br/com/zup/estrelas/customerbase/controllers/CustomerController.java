package br.com.zup.estrelas.customerbase.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.customerbase.dto.CustomerDTO;
import br.com.zup.estrelas.customerbase.entities.Customer;
import br.com.zup.estrelas.customerbase.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping ("/customers")
public class CustomerController {
	
	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService service;
	
	@ApiOperation(value="Create a new customer")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public String isert(@Valid @RequestBody CustomerDTO insertCustomerDTO){
		logger.info("ENTERED IN CUSTOMER CREAT METHOD.");
		return service.insert(insertCustomerDTO);
	}
	
	@ApiOperation(value="List all customers")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Customer> findAll(){
		logger.info("ENTERED IN LIST ALL CUSTOMER METHOD.");
		return service.findAll();
	}
	
	@ApiOperation(value="Find a customer")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/{cpf}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Customer find (@PathVariable String cpf){
		logger.info("GET | CPF: " + cpf);
		return service.find(cpf);
	}
	
	@ApiOperation(value="Update a customer data")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(path = "/{cpf}")
	public String updateData (@PathVariable String cpf, @Valid @RequestBody CustomerDTO customerDTO){
		logger.info("GET | CPF: " + cpf);
		logger.info("UPDATE " + customerDTO.toString());
		return service.updateData(cpf, customerDTO);
	}
	
	@ApiOperation(value="Delete a customer")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/{cpf}")
	public void delete (@PathVariable String cpf){
		logger.info("DELETE | CPF: " + cpf);
		service.delete(cpf);
	}

}
