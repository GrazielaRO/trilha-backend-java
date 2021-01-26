package br.com.zup.estrelas.customerbase.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.estrelas.customerbase.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{
	
	boolean existsByCpf(String cpf);
	
	Optional<Customer> findByCpf(String cpf);
	
}
