package se.lexicon.erik.gastronmic.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("api/recipes")
	public ResponseEntity<?> create(@Valid @RequestBody RecipeDto dto){
		if(dto == null) {
			throw new IllegalArgumentException("Requestbody was " + dto);
		}
		Recipe newRecipe = converter.dtoToRecipe(dto);
		newRecipe = recipeRepo.save(newRecipe);
		StringBuilder sb = new StringBuilder("api/recipes/");
		sb.append(newRecipe.getRecipeId());
		return ResponseEntity.created(URI.create(sb.toString())).build();
	}
	
	@PutMapping("api/recipes/{recipeId}")
	public ResponseEntity<RecipeDto> update(@PathVariable("recipeId") int recipeId, @Valid @RequestBody RecipeDto updatedDto){
		if(recipeId != updatedDto.getRecipeId()) {
			throw new IllegalArgumentException();
		}
		Recipe updatedRecipe = converter.dtoToRecipe(updatedDto);
		
		updatedRecipe = recipeRepo.save(updatedRecipe);
		
		updatedDto = converter.recipeToDto(updatedRecipe);
		
		return ResponseEntity.ok(updatedDto);
	}
	
	@GetMapping("api/recipes/{recipeId}")
	public ResponseEntity<?> find(@PathVariable("recipeId") int recipeId){
		Optional<Recipe> optional = recipeRepo.findById(recipeId);
		
		return optional.isPresent() ? ResponseEntity.ok(converter.recipeToDto((optional.get()))) : ResponseEntity.notFound().build();		
	}	
}
