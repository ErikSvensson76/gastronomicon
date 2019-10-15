package se.lexicon.erik.gastronmic;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.erik.gastronmic.data.IngredientRepo;
import se.lexicon.erik.gastronmic.data.RecipeRepository;
import se.lexicon.erik.gastronmic.model.Ingredient;
import se.lexicon.erik.gastronmic.model.Instruction;
import se.lexicon.erik.gastronmic.model.MeasuredIngredient;
import se.lexicon.erik.gastronmic.model.Measurement;
import se.lexicon.erik.gastronmic.model.Recipe;

@Component
@Transactional
public class Seeder implements CommandLineRunner{
	
	private IngredientRepo ingredientRepo;
	private RecipeRepository recipeRepo;
	
	
	@Autowired
	public Seeder(IngredientRepo ingredientRepo, RecipeRepository recipeRepo) {
		this.ingredientRepo = ingredientRepo;
		this.recipeRepo = recipeRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		createIngredients();
		createRecipes();
	}

	private void createRecipes() {
		Recipe recipe = new Recipe("Pasta Carbonara", "Italian Pasta Carmonara Simon style");
		Ingredient cheese = new Ingredient("Parmesan");
		Ingredient egg = new Ingredient("Egg");
		Ingredient pasta = new Ingredient("Spagetti");
		Ingredient water = new Ingredient("Water");
		Ingredient salt = new Ingredient("Salt");
		Ingredient pepper = new Ingredient("Black pepper");
		Ingredient garlic = ingredientRepo.findByName("Garlic").get();
		Ingredient ham = ingredientRepo.findByName("Ham").get();
		
		MeasuredIngredient[] ingredients = {
			new MeasuredIngredient(cheese, 2, Measurement.DL),
			new MeasuredIngredient(egg, 4, Measurement.ST),
			new MeasuredIngredient(pasta, 500, Measurement.G),
			new MeasuredIngredient(water, 1, Measurement.DL),
			new MeasuredIngredient(salt, 0.5, Measurement.TSK),
			new MeasuredIngredient(pepper, 1, Measurement.TSK),
			new MeasuredIngredient(ham, 250, Measurement.G),
			new MeasuredIngredient(garlic, 2, Measurement.ST)	
		};
		
		Instruction[] instructions = {
				new Instruction("Börja med att koka spagettin, glöm inte bort att spara 1 dl"),
				new Instruction("Blanda ihop osten med äggen, låt stå en liten stund"),
				new Instruction("Stek skinkan knaprig"),
				new Instruction("Tillsätt vitlöken riven"),
				new Instruction("Tilsätt den färdiga spagettin"),
				new Instruction("Blanda i osten och äggen"),
				new Instruction("Tillsätt det sparade spagettivattnet"),
				new Instruction("Krydda med salt och peppar"),
				new Instruction("Bon appitite!")		
		};
		
		for(MeasuredIngredient ingredient : ingredients) {
			recipe.addIngredient(ingredient);
		}
		
		for(Instruction instruction : instructions) {
			recipe.addInstruction(instruction);
		}
		
		recipeRepo.save(recipe);		
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
		ingredientRepo.saveAll(ingredients);
	}

}
