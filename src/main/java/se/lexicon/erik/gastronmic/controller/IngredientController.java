package se.lexicon.erik.gastronmic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.lexicon.erik.gastronmic.converters.EntityDtoConverter;
import se.lexicon.erik.gastronmic.data.IngredientRepo;
import se.lexicon.erik.gastronmic.dto.IngredientDto;
import se.lexicon.erik.gastronmic.model.Ingredient;

@RestController
public class IngredientController {
	
	private IngredientRepo ingredientRepo;
	private EntityDtoConverter converter;
	
	@Autowired
	public IngredientController(IngredientRepo ingredientRepo, EntityDtoConverter converter) {
		this.ingredientRepo = ingredientRepo;
		this.converter = converter;
	}

	@GetMapping("api/ingredients")
	public ResponseEntity<?> find(@RequestParam(defaultValue = "all") String param){
		if(param.equals("all")) {
			List<Ingredient> ingredients = (List<Ingredient>) ingredientRepo.findAll();
			return ResponseEntity.ok().body(converter.ingredientToDto(ingredients));
		}
		
		
		Optional<Ingredient> optional = ingredientRepo.findByName(param);
		
		return optional.isPresent() ? ResponseEntity.ok(converter.ingredientToDto(optional.get())) : ResponseEntity.notFound().build();		
	}
	
	@PostMapping("api/ingredients")
	public ResponseEntity<Ingredient> create(@Valid @RequestBody IngredientDto ingredient){
		if(ingredient == null) {
			throw new IllegalArgumentException();
		}
		return ResponseEntity.ok(ingredientRepo.save(converter.dtoToIngredient(ingredient)));
	}
	
	@GetMapping("api/ingredients/{id}")
	public ResponseEntity<IngredientDto> findById(@PathVariable("id")int id){
		Optional<Ingredient> optional = ingredientRepo.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(converter.ingredientToDto(optional.get()));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("api/ingredients/{id}")
	public ResponseEntity<IngredientDto> update(@PathVariable("id")int id, @ RequestBody IngredientDto updated){
		Ingredient old = ingredientRepo.findById(id).orElseThrow(IllegalArgumentException::new);
		if(id != updated.getId()) {
			throw new SecurityException();
		}
		
		old.setName(updated.getName());
		old = ingredientRepo.save(old);
		return ResponseEntity.ok(converter.ingredientToDto(old));		
	}
	
	@DeleteMapping("api/ingredients/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id){
		ingredientRepo.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> validationErrorOnIngredient(MethodArgumentNotValidException e){
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











