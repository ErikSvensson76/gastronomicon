package se.lexicon.erik.gastronmic.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import se.lexicon.erik.gastronmic.dto.IngredientDto;
import se.lexicon.erik.gastronmic.model.EntityFactory;
import se.lexicon.erik.gastronmic.model.Ingredient;

@Component
public class EntityDtoConverter extends EntityFactory{
	
	public IngredientDto ingredientToDto(Ingredient ingredient) {
		IngredientDto dto = new IngredientDto();
		dto.setId(ingredient.getId());
		dto.setName(ingredient.getName());
		return dto;				
	}
	
	public List<IngredientDto> ingredientToDto(List<Ingredient> ingredients){
		List<IngredientDto> dtos = new ArrayList<>();
		for(Ingredient ingredient : ingredients) {
			dtos.add(ingredientToDto(ingredient));
		}
		return dtos;
	}
	
	public Ingredient dtoToIngredient(IngredientDto dto) {
		Ingredient ingredient = createIngredient(dto.getId(), dto.getName());
		return ingredient;
	}
}
