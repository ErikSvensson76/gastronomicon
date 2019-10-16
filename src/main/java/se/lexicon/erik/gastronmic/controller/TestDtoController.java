package se.lexicon.erik.gastronmic.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import se.lexicon.erik.gastronmic.dto.TestDto;

@RestController
public class TestDtoController {
	
	@PostMapping("api/tests")
	public ResponseEntity<TestDto> create(@Valid @RequestBody TestDto dto){
		return ResponseEntity.ok(dto);		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> testDtoValidationError(MethodArgumentNotValidException e){
		Map<String, Object> errors = new HashMap<>();
		errors.put("code", 400);
		errors.put("status", HttpStatus.BAD_REQUEST);
		e.getBindingResult().getAllErrors().forEach(error ->{
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return ResponseEntity.badRequest().body(errors);
	}

}
