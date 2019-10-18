package se.lexicon.erik.gastronmic.validation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.lexicon.erik.gastronmic.data.IngredientRepo;
import se.lexicon.erik.gastronmic.model.Ingredient;

@Component
public class UniqueNameValidator implements ConstraintValidator<UniqueName, String>{
	
	private IngredientRepo ingredientRepo;

	@Autowired
	public UniqueNameValidator(IngredientRepo ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		Optional<Ingredient> optional = ingredientRepo.findByName(name);
		return !optional.isPresent();
	}

}
