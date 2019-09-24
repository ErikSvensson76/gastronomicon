package se.lexicon.erik.gastronmic.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.erik.gastronmic.model.Ingredient;

public interface IngredientRepo extends CrudRepository<Ingredient, Integer>{
	
	Optional<Ingredient> findByName(String name);

}
