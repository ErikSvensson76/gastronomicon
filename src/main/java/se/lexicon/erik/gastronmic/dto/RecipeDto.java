package se.lexicon.erik.gastronmic.dto;

import java.util.List;

public class RecipeDto {
	
	private int recipeId;
	private String recipeName;
	private String description;
	private List<MeasuredIngredientDto> ingredients;
	private List<InstructionDto> instructions;
	
	public RecipeDto() {
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MeasuredIngredientDto> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<MeasuredIngredientDto> ingredients) {
		this.ingredients = ingredients;
	}

	public List<InstructionDto> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<InstructionDto> instructions) {
		this.instructions = instructions;
	}
}
