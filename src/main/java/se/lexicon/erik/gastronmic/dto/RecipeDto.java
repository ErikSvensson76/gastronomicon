package se.lexicon.erik.gastronmic.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

public class RecipeDto {
	
	
	private int recipeId;	
	@NotNull(message = "Recipe name is required")
	@Size(min = 2, max = 255, message = "RecipeName is not between 2 and 255 letters")
	private String recipeName;
	@NotNull(message = "Description is required")
	@Size(min = 2, max = 255, message = "Description is not between 2 and 255 letters")
	private String description;
	@Nullable
	private List<MeasuredIngredientDto> ingredients;
	@Nullable
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
