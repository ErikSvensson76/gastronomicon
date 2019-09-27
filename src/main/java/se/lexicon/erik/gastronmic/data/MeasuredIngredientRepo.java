package se.lexicon.erik.gastronmic.data;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.erik.gastronmic.model.MeasuredIngredient;

public interface MeasuredIngredientRepo extends CrudRepository<MeasuredIngredient, Integer>{

}
