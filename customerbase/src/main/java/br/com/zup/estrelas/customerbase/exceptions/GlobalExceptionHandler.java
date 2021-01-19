package br.com.zup.estrelas.customerbase.exceptions;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zup.estrelas.customerbase.dto.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody List<ErrorDTO> handleValidationError(MethodArgumentNotValidException e) {

        List<ErrorDTO> validationErrors = new ArrayList<>();

        for (ObjectError error : e.getBindingResult().getAllErrors()) {

            if (nonNull(error.getCodes()) && nonNull(error.getCodes()[0])) {
            	String fieldName = error.getCodes()[0];
            	
            	StringBuilder messageDisplayed = new StringBuilder();
				messageDisplayed.append("[");
				messageDisplayed
						.append(fieldName.substring(fieldName.lastIndexOf(".") + 1).toUpperCase());
				messageDisplayed.append("] - ");
				messageDisplayed.append(error.getDefaultMessage());
                validationErrors.add(new ErrorDTO(messageDisplayed.toString()));
            }
        }
        logger.error("VALIDATION ERROR.");
        return validationErrors;
    }
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NotFoundException.class })
	public @ResponseBody ErrorDTO handlerBusinessRules(NotFoundException exception) {
		logger.error("CUSTOMER NOT FOUND.");
		return new ErrorDTO(exception.getMessage());
	}
	
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler({ CustomerAlreadyExistsException.class })
	public @ResponseBody ErrorDTO handlerBusinessRules(CustomerAlreadyExistsException exception) {
		logger.error("CUSTUMER ALREADY EXISTS IN THE DATABASE.");
		return new ErrorDTO(exception.getMessage());
	}

}
