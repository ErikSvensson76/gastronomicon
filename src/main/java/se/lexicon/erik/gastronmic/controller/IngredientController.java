package se.lexicon.erik.gastronmic.controller;

import static se.lexicon.erik.gastronmic.converters.IngredientDtoConverter.convert;
import static se.lexicon.erik.gastronmic.converters.IngredientDtoConverter.dtoToIngredient;
import static se.lexicon.erik.gastronmic.converters.IngredientDtoConverter.ingredientToDto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.lexicon.erik.gastronmic.converters.IngredientDtoConverter;
import se.lexicon.erik.gastronmic.data.IngredientRepo;
import se.lexicon.erik.gastronmic.dto.IngredientDto;
import se.lexicon.erik.gastronmic.model.Ingredient;

@RestController
public class IngredientController {
	
	private IngredientRepo ingredientRepo;
	
		
	@Autowired
	public IngredientController(IngredientRepo ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	@GetMapping("api/ingredients")
	public ResponseEntity<?> find(@RequestParam(defaultValue = "all") String param){
		if(param.equals("all")) {
			List<Ingredient> ingredients = (List<Ingredient>) ingredientRepo.findAll();
			return ResponseEntity.ok().body(ingredientToDto(ingredients));
		}
		
		
		Optional<Ingredient> optional = ingredientRepo.findByName(param);
		
		return optional.isPresent() ? ResponseEntity.ok(convert(IngredientDtoConverter::ingredientToDto, optional.get())) : ResponseEntity.notFound().build();		
	}
	
	@PostMapping("api/ingredients")
	public ResponseEntity<Ingredient> create(@RequestBody IngredientDto ingredient){
		if(ingredient == null) {
			throw new IllegalArgumentException();
		}
		return ResponseEntity.ok(ingredientRepo.save(dtoToIngredient(ingredient)));
	}
	
	@GetMapping("api/ingredients/{id}")
	public ResponseEntity<IngredientDto> findById(@PathVariable("id")int id){
		Optional<Ingredient> optional = ingredientRepo.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(ingredientToDto(optional.get()));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("api/ingredients/{id}")
	public ResponseEntity<Ingredient> update(@PathVariable("id")int id, @ RequestBody Ingredient updated){
		Ingredient old = ingredientRepo.findById(id).orElseThrow(IllegalArgumentException::new);
		if(id != updated.getId()) {
			throw new SecurityException();
		}
		
		old.setName(updated.getName());
		return ResponseEntity.ok(ingredientRepo.save(old));		
	}
	
	@DeleteMapping("api/ingredients/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id){
		ingredientRepo.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}











