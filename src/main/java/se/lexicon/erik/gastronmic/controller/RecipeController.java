package se.lexicon.erik.gastronmic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import se.lexicon.erik.gastronmic.data.RecipeRepository;

@RestController
public class RecipeController {
	
	private RecipeRepository recipeRepo;

	@Autowired
	public RecipeController(RecipeRepository recipeRepo) {
		this.recipeRepo = recipeRepo;
	}
	
	
	
	
}
