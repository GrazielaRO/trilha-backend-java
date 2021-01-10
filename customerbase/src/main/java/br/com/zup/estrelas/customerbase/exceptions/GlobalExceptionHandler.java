package br.com.zup.estrelas.customerbase.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zup.estrelas.customerbase.dto.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(BusinessRuleException.class)
    public @ResponseBody ErrorDTO handleGenericException(BusinessRuleException e) {
        e.printStackTrace();
        return new ErrorDTO(e.getErrorMessage());
    }

}
