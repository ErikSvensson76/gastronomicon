package se.lexicon.erik.gastronmic.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import se.lexicon.erik.gastronmic.dto.IngredientDto;
import se.lexicon.erik.gastronmic.model.Ingredient;

public class IngredientDtoConverter {
	
	public static IngredientDto ingredientToDto(Ingredient ingredient) {
		IngredientDto dto = new IngredientDto();
		dto.setId(ingredient.getId());
		dto.setName(ingredient.getName());
		return dto;				
	}
	
	public static List<IngredientDto> ingredientToDto(List<Ingredient> ingredients){
		List<IngredientDto> dtos = new ArrayList<>();
		for(Ingredient ingredient : ingredients) {
			dtos.add(ingredientToDto(ingredient));
		}
		return dtos;
	}
	
	public static Ingredient dtoToIngredient(IngredientDto dto) {
		Ingredient ingredient = new Ingredient(dto.getId(), dto.getName());
		return ingredient;
	}
	
	public static <T,R> R convert(Function<T, R> converter, T toConvert) {
		return converter.apply(toConvert);
	}

}
