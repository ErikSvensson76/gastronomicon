package se.lexicon.erik.gastronmic.model;

import java.util.List;


public abstract class EntityFactory {
	
	protected Ingredient createIngredient(int id, String name) {
		return new Ingredient(id, name);
	}
	
	protected Instruction createInstruction(int id, String textContent, Recipe owner) {
		return new Instruction(id, textContent, owner);
	}
	
	protected MeasuredIngredient createMeasuredIngredient(int id, Ingredient ingredient, Recipe recipe, double amount, Measurement measurement) {
		return new MeasuredIngredient(id, ingredient, recipe, amount, measurement);
	}
	
	protected Recipe createRecipe(int recipeId, String recipeName, String description, List<MeasuredIngredient> ingredients,
			List<Instruction> instructions) {
		return new Recipe(recipeId, recipeName, description, ingredients, instructions);
	}

}
