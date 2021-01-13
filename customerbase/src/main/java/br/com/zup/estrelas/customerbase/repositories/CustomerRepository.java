package br.com.zup.estrelas.customerbase.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.estrelas.customerbase.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String>{
	
	boolean existsByCpf(String cpf);
	
	Optional<Customer> findByCpf(String cpf);
	
}
