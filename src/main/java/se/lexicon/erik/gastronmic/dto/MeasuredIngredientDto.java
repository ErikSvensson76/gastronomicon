package se.lexicon.erik.gastronmic.dto;

import se.lexicon.erik.gastronmic.model.Measurement;

public class MeasuredIngredientDto {
	
	private int id;
	private IngredientDto ingredient;
	private double amount;
	private Measurement measurement;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public IngredientDto getIngredient() {
		return ingredient;
	}
	public void setIngredient(IngredientDto ingredient) {
		this.ingredient = ingredient;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Measurement getMeasurement() {
		return measurement;
	}
	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}
}
