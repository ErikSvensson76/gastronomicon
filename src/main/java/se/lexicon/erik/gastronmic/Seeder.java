package se.lexicon.erik.gastronmic;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import se.lexicon.erik.gastronmic.data.IngredientRepo;
import se.lexicon.erik.gastronmic.model.Ingredient;

@Component
public class Seeder implements CommandLineRunner{
	
	private IngredientRepo repo;
	
	@Autowired
	public Seeder(IngredientRepo repo) {
		this.repo = repo;
	}

	@Override
	public void run(String... args) throws Exception {
		createIngredients();		
	}

	private void createIngredients() {
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("Tomato"),
				new Ingredient("Tofu"),
				new Ingredient("Broccoli"),
				new Ingredient("Ham"),
				new Ingredient("Basil"),
				new Ingredient("Garlic"),
				new Ingredient("Chocolate")
			);
		repo.saveAll(ingredients);
	}

}
