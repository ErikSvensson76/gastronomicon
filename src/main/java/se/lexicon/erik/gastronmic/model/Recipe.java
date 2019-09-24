package se.lexicon.erik.gastronmic.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
	
	private int recipeId;
	private String recipeName;
	private String description;
	private List<MeasuredIngredient> ingredients;
	private List<Instruction> instructions;
	
		
	public Recipe(String recipeName, String description, List<MeasuredIngredient> ingredients,
			List<Instruction> instructions) {
		this.recipeName = recipeName;
		this.description = description;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}

	public Recipe(String recipeName, String description) {
		this(recipeName, description, new ArrayList<>(), new ArrayList<>());
		this.recipeName = recipeName;
		this.description = description;
	}
	
	public Recipe () {}

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

	public List<MeasuredIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<MeasuredIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public int getRecipeId() {
		return recipeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + recipeId;
		result = prime * result + ((recipeName == null) ? 0 : recipeName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (recipeId != other.recipeId)
			return false;
		if (recipeName == null) {
			if (other.recipeName != null)
				return false;
		} else if (!recipeName.equals(other.recipeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Recipe [recipeId=");
		builder.append(recipeId);
		builder.append(", recipeName=");
		builder.append(recipeName);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
}
