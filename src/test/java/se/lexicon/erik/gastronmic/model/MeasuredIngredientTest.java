package se.lexicon.erik.gastronmic.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MeasuredIngredientTest {
	
	private MeasuredIngredient testObject;
	private Ingredient ingredient;
	
	@Before
	public void setup() {
		ingredient = new Ingredient("Spam");
		Recipe recipe = new Recipe("Test recipe", "Recipe description");
		testObject = new MeasuredIngredient(ingredient, 2, Measurement.KG);
		recipe.addIngredient(testObject);
	}
	
	@Test
	public void testObject_successfully_created() {
		assertEquals(0, testObject.getId());
		assertEquals(2, testObject.getAmount(),0);
		assertEquals(Measurement.KG, testObject.getMeasurement());
		assertNotNull(testObject.getIngredient());
		assertNotNull(testObject.getRecipe());
	}
	
	@Test
	public void copy_equals_testObject_true() {
		Ingredient ingredient = new Ingredient("Spam");
		Recipe recipe = new Recipe("Test recipe", "Recipe description");
		MeasuredIngredient copy = new MeasuredIngredient(ingredient, 2, Measurement.KG);
		recipe.addIngredient(copy);
		
		assertEquals(copy, testObject);
		assertEquals(copy.hashCode(), testObject.hashCode());
	}
	
	@Test
	public void toString_contains_correct_data() {
		String toString = testObject.toString();
		assertTrue(
				toString.contains("0") &&
				toString.contains(ingredient.toString()) &&
				toString.contains("2.0") &&
				toString.contains(Measurement.KG.name())
			);
	}

}
