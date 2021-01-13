package br.com.zup.estrelas.customerbase.controllers;

import java.util.List;

import javax.validation.Valid;

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

@RestController
@RequestMapping ("/customers")
public class CustomerController {
	
	@Autowired
	CustomerService service;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public String isert(@Valid @RequestBody CustomerDTO insertCustomerDTO){
		return service.insert(insertCustomerDTO);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Customer> findAll(){
		return service.findAll();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/{cpf}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Customer find (@PathVariable String cpf){
		return service.find(cpf);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(path = "/{cpf}")
	public String updateData (@PathVariable String cpf, @Valid @RequestBody CustomerDTO customerDTO){
		return service.updateData(cpf, customerDTO);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/{cpf}")
	public void delete (@PathVariable String cpf){
		service.delete(cpf);
	}

}
