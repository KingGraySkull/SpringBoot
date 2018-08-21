package validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import util.NotEmptyOrNull;


public class TextFieldValidator implements ConstraintValidator<NotEmptyOrNull, String>
{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context)
	{
		boolean isEmptyOrNull = true;
		
		if(value.isEmpty() || value == null)
		{
			isEmptyOrNull = false;
		}
		return isEmptyOrNull;
	}
	
}