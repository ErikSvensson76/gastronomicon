package se.lexicon.erik.gastronmic.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class TestDto {
	
	@PositiveOrZero(message = "TestId can't be negative")
	private int testId;
	
	@NotEmpty(message = "This field is mandatory")
	@Size(min = 2, max = 100, message = "Name need to be between 2 and 100 letters")
	private String name;	
	private boolean active;
	
	@Email(message = "email must be a proper structure"
			, regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
	private String email;
	
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
