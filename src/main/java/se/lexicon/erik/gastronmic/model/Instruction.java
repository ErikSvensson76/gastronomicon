package se.lexicon.erik.gastronmic.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Instruction {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	private String textContent;
	@ManyToOne(fetch = FetchType.LAZY, 
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "recipe_id")
	private Recipe owner;
	
	public Instruction(String textContent) {
		this.textContent = textContent;
	}
	
	public Instruction() {}
	
	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public Recipe getRecipe() {
		return owner;
	}

	public void setRecipe(Recipe recipe) {
		this.owner = recipe;
	}

	public int getId() {
		return id;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((textContent == null) ? 0 : textContent.hashCode());
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
		Instruction other = (Instruction) obj;
		if (id != other.id)
			return false;
		if (textContent == null) {
			if (other.textContent != null)
				return false;
		} else if (!textContent.equals(other.textContent))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Instruction [id=");
		builder.append(id);
		builder.append(", textContent=");
		builder.append(textContent);
		builder.append("]");
		return builder.toString();
	}
}
