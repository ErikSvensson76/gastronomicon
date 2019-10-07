package se.lexicon.erik.gastronmic.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTest {
	
	private Ingredient testObject;
	
	@Before
	public void setup() {
		testObject = new Ingredient("Spam");
	}
	
	@Test
	public void testObject_successfully_created() {
		assertEquals(0, testObject.getId());
		assertEquals("Spam", testObject.getName());
	}
	
	@Test
	public void copy_equals_testObject_true() {
		Ingredient copy = new Ingredient("Spam");
		assertTrue(copy.equals(testObject));
		assertEquals(copy.hashCode(), testObject.hashCode());
	}
	
	@Test
	public void toString_contains_correct_data() {
		String toString = testObject.toString();
		assertTrue(
			toString.contains("0") &&
			toString.contains("Spam")
		);		
	}

}
