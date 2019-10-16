package se.lexicon.erik.gastronmic.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import se.lexicon.erik.gastronmic.converters.EntityDtoConverter;
import se.lexicon.erik.gastronmic.model.EntityFactory;
import se.lexicon.erik.gastronmic.model.Ingredient;
import se.lexicon.erik.gastronmic.model.Instruction;
import se.lexicon.erik.gastronmic.model.MeasuredIngredient;
import se.lexicon.erik.gastronmic.model.Measurement;
import se.lexicon.erik.gastronmic.model.Recipe;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EntityDtoConverterTest extends EntityFactory{
	
	@Autowired
	private EntityDtoConverter testObject;
	
	private RecipeDto createRecipeDto() {
		IngredientDto ingredient1 = new IngredientDto();
		ingredient1.setId(1); ingredient1.setName("Spam");
		IngredientDto ingredient2 = new IngredientDto();
		ingredient2.setId(2); ingredient2.setName("Water");
		
		MeasuredIngredientDto mIngr1 = new MeasuredIngredientDto();
		mIngr1.setId(1); mIngr1.setIngredient(ingredient1); mIngr1.setAmount(2); mIngr1.setMeasurement(Measurement.DL);
		MeasuredIngredientDto mIngr2 = new MeasuredIngredientDto();
		mIngr2.setId(2); mIngr2.setIngredient(ingredient2); mIngr2.setAmount(0.5); mIngr1.setMeasurement(Measurement.L);
		
		InstructionDto instr1 = new InstructionDto();
		instr1.setId(1); instr1.setTextContent("Add Spam");
		InstructionDto instr2 = new InstructionDto();
		instr2.setId(2); instr2.setTextContent("Mix water with Spam");
		InstructionDto instr3 = new InstructionDto();
		instr3.setId(3);instr3.setTextContent("Eat");
		
		List<MeasuredIngredientDto> ingredients = Arrays.asList(mIngr1, mIngr2);
		List<InstructionDto> instructions = Arrays.asList(instr1, instr2, instr3);
		
		RecipeDto dto = new RecipeDto();
		dto.setRecipeId(1);
		dto.setRecipeName("Spam and water");
		dto.setDescription("test");
		dto.setIngredients(ingredients);
		dto.setInstructions(instructions);
		return dto;
	}
	
	private Recipe createRecipe() {
		Ingredient ingredient = createIngredient(1, "Spam");
		Ingredient ingredient2 = createIngredient(2, "Water");
		
		MeasuredIngredient[] ingredients = {
				createMeasuredIngredient(1, ingredient, null, 2, Measurement.DL),
				createMeasuredIngredient(2, ingredient2, null, 0.5, Measurement.L)
		};
		
		Instruction[] instructions = {
				createInstruction(1, "Add Spam", null),
				createInstruction(2, "Mix water with Spam", null),
				createInstruction(3, "Eat", null)
		};
		
		Recipe recipe = createRecipe(1, "Spam and water", "test", Arrays.asList(ingredients), Arrays.asList(instructions));
		return recipe;
		
	}
	
	private RecipeDto recipeDto;
	private Recipe recipe;
	
	@Before
	public void setup() {
		recipeDto = createRecipeDto();
		recipe = createRecipe();
	}
	
	@Test
	public void given_recipe_returns_recipeDto() {
		
		RecipeDto actual = testObject.recipeToDto(recipe);
		assertEquals("Spam", actual.getIngredients().get(0).getIngredient().getName());
		assertEquals("Water", actual.getIngredients().get(1).getIngredient().getName());
		assertEquals("Add Spam", actual.getInstructions().get(0).getTextContent());
		assertEquals("Mix water with Spam", actual.getInstructions().get(1).getTextContent());
		assertEquals("Eat", actual.getInstructions().get(2).getTextContent());
		assertEquals(1, actual.getRecipeId());
		assertEquals("Spam and water", actual.getRecipeName());
		assertEquals("test", actual.getDescription());		
	}
	
	@Test
	public void given_recipeDto_returns_recipe() {
		Recipe actual = testObject.dtoToRecipe(recipeDto);
		
		assertEquals(recipe.toString(), actual.toString());
		assertTrue(actual.getIngredients().stream()
				.allMatch(ingredient -> ingredient.getRecipe().equals(recipe)));
		assertTrue(actual.getInstructions().stream()
				.allMatch(instruction -> instruction.getRecipe().equals(recipe)));
		
	}
	
	
}
