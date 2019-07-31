package entities;

public class CustomField {
	
	private String fieldName;
	private boolean required;
	private boolean defaultValue;
	private char padValue;
	private char padDirection;
	
	public CustomField() {}

	public CustomField(String fieldName, boolean required, boolean defaultValue, char padValue,
			char padDirection) {
		this.fieldName = fieldName;
		this.required = required;
		this.defaultValue = defaultValue;
		this.padValue = padValue;
		this.padDirection = padDirection;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required.equalsIgnoreCase("true") ? true : false;
	}

	public boolean isDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue.equalsIgnoreCase("true") ? true : false;
	}

	public char getPadValue() {
		return padValue;
	}

	public void setPadValue(String padValue) {
		this.padValue = padValue.charAt(0);
	}

	public char getPadDirection() {
		return padDirection;
	}

	public void setPadDirection(String padDirection) {
		this.padDirection = padDirection.charAt(0);
	}
}
