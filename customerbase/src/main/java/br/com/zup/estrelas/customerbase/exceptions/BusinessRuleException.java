package br.com.zup.estrelas.customerbase.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BusinessRuleException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String errorMessage;

}
