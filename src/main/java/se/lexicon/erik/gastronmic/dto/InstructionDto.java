package se.lexicon.erik.gastronmic.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class InstructionDto {
	
	@PositiveOrZero
	private int id;
	@NotNull(message = "Text content is required")
	@Size(min = 2, max = 255, message = "Text content need to have between 2 and 255 letters")
	private String textContent;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	
	

}
