package pojos;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class Person {

	private String name = "Default";
	private String surname = "Default";
	private LocalDate date = LocalDate.now();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
}
