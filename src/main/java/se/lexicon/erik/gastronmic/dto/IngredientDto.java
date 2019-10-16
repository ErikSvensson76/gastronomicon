package se.lexicon.erik.gastronmic.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import se.lexicon.erik.gastronmic.validation.UniqueName;

public class IngredientDto {
	
	private int id;
	@NotEmpty(message = "Name field is required")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 letters")
	@UniqueName
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
