package se.lexicon.erik.gastronmic.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.erik.gastronmic.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Integer>{
	
	List<Recipe> findByRecipeName(String recipeName);
	List<Recipe> findByIngredientsIngredientName(String ingredientName);
 
}
