package dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import util.NotEmptyOrNull;
import util.PasswordMatcher;
import util.ValidEmail;

@PasswordMatcher
public class UserDto
{
	@NotEmptyOrNull
	private String firstName;
	
	@NotEmptyOrNull
	private String lastName;

	@NotEmptyOrNull
	private String password;
	
	@NotEmptyOrNull
	private String matchingPassword;
	
	@NotEmpty(message = "Field cannot be empty!")
	@ValidEmail
	private String email;
	
	public UserDto() {}

	public UserDto(@NotNull @NotEmpty String firstName, @NotNull @NotEmpty String lastName,
			@NotNull @NotEmpty String password, @NotNull @NotEmpty String matchingPassword,
			@NotNull @NotEmpty String email)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.email = email;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getMatchingPassword()
	{
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword)
	{
		this.matchingPassword = matchingPassword;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Override
	public String toString()
	{
		return "UserDto [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", matchingPassword=" + matchingPassword + ", email=" + email + "]";
	}
}
