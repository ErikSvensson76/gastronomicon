package se.lexicon.erik.gastronmic.data;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.erik.gastronmic.model.Ingredient;
import se.lexicon.erik.gastronmic.model.Instruction;
import se.lexicon.erik.gastronmic.model.MeasuredIngredient;
import se.lexicon.erik.gastronmic.model.Measurement;
import se.lexicon.erik.gastronmic.model.Recipe;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class RecipeRepoTest {
	
	@Autowired
	private RecipeRepository testRepo;
	
	private Recipe testRecipe;
	
	@Before
	public void setup() {
		Recipe recipe = new Recipe("Test name", "Test description");
		Instruction instruction = new Instruction("Instruction 1");
		Instruction instruction2 = new Instruction("Instruction 2");
		Ingredient ingredient = new Ingredient("Spam");
		MeasuredIngredient m_ingredient = new MeasuredIngredient(ingredient, 2, Measurement.KG);
		recipe.addInstruction(instruction);
		recipe.addInstruction(instruction2);
		recipe.addIngredient(m_ingredient);
		testRecipe = testRepo.save(recipe);		
	}
	
	@Test
	public void given_Test_name_should_return_list_of_size_1() {
		String param = "Test name";
		int expectedSize = 1;
		
		List<Recipe> result = testRepo.findByRecipeName(param);
		
		assertEquals(expectedSize, result.size());
		assertTrue(result.contains(testRecipe));
	}
	
	@Test
	public void given_spam_should_return_list_of_1() {
		String nameParam = "Spam";
		int expectedSize = 1;
		
		List<Recipe> result = testRepo.findByIngredientsIngredientName(nameParam);
		
		assertEquals(expectedSize, result.size());
		assertTrue(result.contains(testRecipe));
	}
	
	

}
