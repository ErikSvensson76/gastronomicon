package se.lexicon.erik.gastronmic.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import se.lexicon.erik.gastronmic.dto.IngredientDto;
import se.lexicon.erik.gastronmic.dto.InstructionDto;
import se.lexicon.erik.gastronmic.dto.MeasuredIngredientDto;
import se.lexicon.erik.gastronmic.dto.RecipeDto;
import se.lexicon.erik.gastronmic.model.EntityFactory;
import se.lexicon.erik.gastronmic.model.Ingredient;
import se.lexicon.erik.gastronmic.model.Instruction;
import se.lexicon.erik.gastronmic.model.MeasuredIngredient;
import se.lexicon.erik.gastronmic.model.Recipe;

@Component
public class EntityDtoConverter extends EntityFactory{
	
	public IngredientDto ingredientToDto(Ingredient ingredient) {
		IngredientDto dto = new IngredientDto();
		dto.setId(ingredient.getId());
		dto.setName(ingredient.getName());
		return dto;				
	}
	
	public List<IngredientDto> ingredientToDto(List<Ingredient> ingredients){
		List<IngredientDto> dtos = new ArrayList<>();
		for(Ingredient ingredient : ingredients) {
			dtos.add(ingredientToDto(ingredient));
		}
		return dtos;
	}
	
	public Ingredient dtoToIngredient(IngredientDto dto) {
		Ingredient ingredient = createIngredient(dto.getId(), dto.getName());
		return ingredient;
	}
	
	public List<InstructionDto> instructionsToDtos(List<Instruction> instructions) {
		List<InstructionDto> dtos = new ArrayList<>();
		for(Instruction instruction : instructions) {
			dtos.add(instructionToDto(instruction));
		}
		return dtos;
	}
	
	public InstructionDto instructionToDto(Instruction instruction) {
		InstructionDto dto = new InstructionDto();
		dto.setId(instruction.getId());
		dto.setTextContent(instruction.getTextContent());
		return dto;
	}
	
	private Instruction dtoToInstruction(InstructionDto dto) {
		return createInstruction(dto.getId(), dto.getTextContent(), null);
	}
	
	public List<Instruction> dtosToInstructions(List<InstructionDto> dtos) {
		List<Instruction> instructions = new ArrayList<>();
		for(InstructionDto dto: dtos) {
			instructions.add(dtoToInstruction(dto));
		}
		return instructions;
	}
	
	public MeasuredIngredientDto measuredIngredientToDto(MeasuredIngredient ingredient) {
		MeasuredIngredientDto dto = new MeasuredIngredientDto();
		dto.setId(ingredient.getId());
		dto.setIngredient(ingredientToDto(ingredient.getIngredient()));
		dto.setAmount(ingredient.getAmount());
		dto.setMeasurement(ingredient.getMeasurement());
		return dto;
	}
	
	public List<MeasuredIngredientDto> measuredIngredientsToDtos(List<MeasuredIngredient> measuredIngredients){
		List<MeasuredIngredientDto> dtos = new ArrayList<>();
		for(MeasuredIngredient measuredIngredient: measuredIngredients) {
			dtos.add(measuredIngredientToDto(measuredIngredient));
		}
		return dtos;
	}
	
	private MeasuredIngredient dtoToMeasuredIngredient(MeasuredIngredientDto dto) {
		return createMeasuredIngredient(
				dto.getId(),
				dtoToIngredient(dto.getIngredient()),
				null,
				dto.getAmount(),
				dto.getMeasurement());
	}
	
	public List<MeasuredIngredient> dtosToMeasuredIngredients(List<MeasuredIngredientDto> dtos){
		List<MeasuredIngredient> measuredIngredients = new ArrayList<>();
		for(MeasuredIngredientDto dto : dtos) {
			measuredIngredients.add(dtoToMeasuredIngredient(dto));
		}
		return measuredIngredients;
	}
	
	public RecipeDto recipeToDto(Recipe recipe) {
		RecipeDto dto = new RecipeDto();
		dto.setRecipeId(recipe.getRecipeId());
		dto.setRecipeName(recipe.getRecipeName());
		dto.setDescription(recipe.getDescription());
		dto.setIngredients(measuredIngredientsToDtos(recipe.getIngredients()));
		dto.setInstructions(instructionsToDtos(recipe.getInstructions()));
		return dto;		
	}
	
	public List<RecipeDto> recipesToDtos(List<Recipe> recipes){
		List<RecipeDto> dtos = new ArrayList<>();
		for(Recipe recipe : recipes) {
			dtos.add(recipeToDto(recipe));
		}
		return dtos;
	}
	
	public Recipe dtoToRecipe(RecipeDto dto) {
		List<Instruction> instructions = new ArrayList<>();
		if(dto.getInstructions() != null) {
			instructions = dtosToInstructions(dto.getInstructions());
		}
		List<MeasuredIngredient> ingredients = new ArrayList<>();
		if(dto.getIngredients() != null) {
			ingredients = dtosToMeasuredIngredients(dto.getIngredients());
		}		
		
		Recipe recipe = createRecipe(dto.getRecipeId(), dto.getRecipeName(), dto.getDescription(), ingredients, instructions);
		return recipe;		
	}
	
	public List<Recipe> dtosToRecipe(List<RecipeDto> dtos){
		List<Recipe> recipes = new ArrayList<>();
		for(RecipeDto dto : dtos) {
			recipes.add(dtoToRecipe(dto));
		}
		return recipes;
	}
}
