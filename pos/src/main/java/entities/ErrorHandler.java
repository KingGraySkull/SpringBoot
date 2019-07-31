package entities;

import org.springframework.http.HttpStatus;

public class ErrorHandler {
	
	private boolean isError;
	private String message;
	private HttpStatus statusCode;
	
	public ErrorHandler(boolean isError, String message, HttpStatus status) {
		this.isError = isError;
		this.message = message;
		this.statusCode = status;
	}
	
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
}
