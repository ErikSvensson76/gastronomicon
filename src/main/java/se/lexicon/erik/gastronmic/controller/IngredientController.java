package se.lexicon.erik.gastronmic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.lexicon.erik.gastronmic.data.IngredientRepo;
import se.lexicon.erik.gastronmic.model.Ingredient;

@RestController
public class IngredientController {
	
	private IngredientRepo ingredientRepo;

	@Autowired
	public IngredientController(IngredientRepo ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	@GetMapping("api/ingredients")
	public ResponseEntity<List<Ingredient>> findAll(){
		List<Ingredient> ingredients = (List<Ingredient>) ingredientRepo.findAll();		
		return ResponseEntity.ok().body(ingredients);
	}
	
	@PostMapping("api/ingredients")
	public ResponseEntity<Ingredient> create(@RequestBody Ingredient ingredient){
		if(ingredient == null) {
			throw new IllegalArgumentException();
		}
		return ResponseEntity.ok(ingredientRepo.save(ingredient));
	}
	
	@GetMapping("api/ingredients/{id}")
	public ResponseEntity<Ingredient> findById(@PathVariable("id")int id){
		Optional<Ingredient> optional = ingredientRepo.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
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
	
	@GetMapping("api/ingredients/name")
	public ResponseEntity<Ingredient> findByName(@RequestParam("name") String name){
		if(name == null) {
			throw new IllegalArgumentException();
		}
		Optional<Ingredient> optional = ingredientRepo.findByName(name);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();		
	}
	
	
	

}











