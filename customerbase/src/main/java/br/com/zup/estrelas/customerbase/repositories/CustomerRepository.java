package br.com.zup.estrelas.customerbase.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.customerbase.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String>{
	
}
