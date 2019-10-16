package se.lexicon.erik.gastronmic.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(value = "se.lexicon.erik.gastronmic")
public class MyExceptionHandler extends ResponseEntityExceptionHandler{
		
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex){
		Map<String, Object> errors = new HashMap<>();
		errors.put("timestamp", LocalDateTime.now());
		errors.put("message", ex.getMessage());
		errors.put("code", HttpStatus.BAD_REQUEST.value());
		errors.put("status", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		
	}

}
