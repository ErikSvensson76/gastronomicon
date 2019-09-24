package se.lexicon.erik.gastronmic.model;

public class MeasuredIngredient {
	
	private String id;
	private Ingredient ingredient;
	private Recipe recipe;
	private double amount;
	private Measurement measurement;
	
	public MeasuredIngredient(Ingredient ingredient, double amount, Measurement measurement) {
		this.ingredient = ingredient;
		this.amount = amount;
		this.measurement = measurement;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
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

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {		
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((measurement == null) ? 0 : measurement.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeasuredIngredient other = (MeasuredIngredient) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (measurement != other.measurement)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MeasuredIngredient [id=");
		builder.append(id);
		builder.append(", ingredient=");
		builder.append(ingredient);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", measurement=");
		builder.append(measurement);
		builder.append("]");
		return builder.toString();
	}
}
