package se.lexicon.erik.gastronmic.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.erik.gastronmic.model.Ingredient;
import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class IngredientRepoTest {
	
	@Autowired  
	private IngredientRepo testObject;
	private Ingredient testIngredient;
	
	@Before
	public void setup() {
		Ingredient ingredient = new Ingredient("Tomato");
		testIngredient = testObject.save(ingredient);
	}
	
	@Test
	public void test_save_ingredient_success() {		
		assertNotNull(testIngredient);
		assertTrue(testIngredient.getId() != 0);
		assertEquals("Tomato", testIngredient.getName());		
	}
	
	@Test
	public void given_tomato_should_return_Optional_with_testIngredient() {
		String ingedientName = "Tomato";
		Optional<Ingredient> result = testObject.findByName(ingedientName);
		
		assertTrue(result.isPresent());
		assertEquals("Tomato", result.get().getName());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
