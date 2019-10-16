package se.lexicon.erik.gastronmic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import se.lexicon.erik.gastronmic.converters.EntityDtoConverter;
import se.lexicon.erik.gastronmic.data.RecipeRepository;
import se.lexicon.erik.gastronmic.dto.RecipeDto;
import se.lexicon.erik.gastronmic.model.Recipe;

@RestController
public class RecipeController {
	
	private RecipeRepository recipeRepo;
	private EntityDtoConverter converter;

	
	@Autowired
	public RecipeController(RecipeRepository recipeRepo, EntityDtoConverter converter) {
		this.recipeRepo = recipeRepo;
		this.converter = converter;
	}

	@GetMapping("api/recipes")
	public ResponseEntity<List<RecipeDto>> find(){		
		List<Recipe> recipes = (List<Recipe>)recipeRepo.findAll();
		List<RecipeDto> dtos = converter.recipesToDtos(recipes);
		return ResponseEntity.ok(dtos);
	}
	
	
	
	
}
