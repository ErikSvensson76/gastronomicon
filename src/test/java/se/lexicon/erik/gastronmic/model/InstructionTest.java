package se.lexicon.erik.gastronmic.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InstructionTest {
	
	private Instruction testObject;
	
	@Before
	public void setup() {
		testObject = new Instruction("Test instruction description");
		Recipe recipe = new Recipe("Test recipe", "Recipe description");
		recipe.addInstruction(testObject);
	}
	
	@Test
	public void testObject_successfully_created() {
		assertEquals(0, testObject.getId());
		assertEquals("Test instruction description", testObject.getTextContent());
		assertNotNull(testObject.getRecipe());
	}
	
	@Test
	public void copy_equals_testObject_true() {		
		Instruction copy = new Instruction("Test instruction description");
		Recipe recipe = new Recipe("Test recipe", "Recipe description");
		recipe.addInstruction(copy);
		
		assertEquals(copy, testObject);
		assertEquals(copy.hashCode(), testObject.hashCode());		
	}
	
	@Test
	public void toString_contains_correct_data() {
		String toString = testObject.toString();
		assertTrue(
				toString.contains("0") &&
				toString.contains("Test instruction description")
			);
	}

}
