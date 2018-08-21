package validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import dtos.UserDto;
import util.PasswordMatcher;

public class PasswordValidator implements ConstraintValidator<PasswordMatcher,UserDto>
{

	@Override
	public boolean isValid(UserDto userDto, ConstraintValidatorContext context)
	{
		if(userDto.getPassword() != null && userDto.getMatchingPassword() != null)
		{
			return (userDto.getPassword().equals(userDto.getMatchingPassword()));
		}
		else 
		{
			return false;
		}
	}

}
