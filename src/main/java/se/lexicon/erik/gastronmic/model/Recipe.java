package se.lexicon.erik.gastronmic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recipeId;
	private String recipeName;
	private String description;
	
	@OneToMany( mappedBy = "recipe", fetch = FetchType.LAZY,
			cascade = CascadeType.ALL, orphanRemoval = true
			)
	
	private List<MeasuredIngredient> ingredients;
	
	@OneToMany( mappedBy = "owner", fetch = FetchType.LAZY,
			cascade = CascadeType.ALL, orphanRemoval = true
			)	
	private List<Instruction> instructions;
	
	Recipe(int recipeId, String recipeName, String description, List<MeasuredIngredient> ingredients, List<Instruction> instructions) {
		if(ingredients == null) throw new IllegalArgumentException("ingredients in Recipe was " + ingredients);
		if(instructions == null) throw new IllegalArgumentException("instructions in Recipe was " + instructions);
		
		this.recipeId = recipeId;
		setRecipeName(recipeName);
		setDescription(description);		
		for(MeasuredIngredient measuredIngredient : ingredients) {
			addIngredient(measuredIngredient);
		}
		
		for(Instruction instruction : instructions) {
			addInstruction(instruction);
		}		
	}	

	public Recipe(String recipeName, String description) {
		setRecipeName(recipeName);
		setDescription(description);
		setIngredients(new ArrayList<>());
		setInstructions(new ArrayList<>());
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
	
	public void addInstruction(Instruction instruction) {
		if(instructions == null) instructions = new ArrayList<>();
		if(!instructions.contains(instruction)) {
			instructions.add(instruction);
			instruction.setRecipe(this);
		}
	}
	
	public void removeInstruction(Instruction toRemove) {
		if(instructions == null) instructions = new ArrayList<>();
		if(instructions.contains(toRemove)) {
			instructions.remove(toRemove);
			toRemove.setRecipe(null);			
		}
	}
	
	public void addIngredient(MeasuredIngredient ingredient) {
		if(ingredients == null) ingredients = new ArrayList<>();
		if(!ingredients.contains(ingredient)) {
			ingredients.add(ingredient);
			ingredient.setRecipe(this);
		}
	}
	
	public void removeIngredient(MeasuredIngredient ingredient) {
		if(ingredients == null) ingredients = new ArrayList<>();
		if(ingredients.contains(ingredient)) {
			ingredients.remove(ingredient);
			ingredient.setRecipe(null);
		}
	}
	
	public void clearIngredients() {
		if(this.ingredients != null) {
			ingredients.forEach(ingredient -> ingredient.setRecipe(null));
			ingredients.clear();			
		}
	}
	
	public void clearInstructions() {
		if(instructions != null) {
			instructions.forEach(instruction -> instruction.setRecipe(null));
			instructions.clear();
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(recipeId, recipeName);
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
		return recipeId == other.recipeId && Objects.equals(recipeName, other.recipeName);
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
