package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import util.ValidEmail;

public class EmailValidator implements ConstraintValidator<ValidEmail, String>
{
	private Pattern pattern;
	
	private Matcher matcher;
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context)
	{
		if(value != null)
		{
			return validateEmail(value);
		}
		else
		{
			return false;
		}
	}
	
	private boolean validateEmail(String email)
	{
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
