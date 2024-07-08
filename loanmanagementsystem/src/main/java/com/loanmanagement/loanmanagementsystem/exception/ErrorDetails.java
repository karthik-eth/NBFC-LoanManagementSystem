package com.loanmanagement.loanmanagementsystem.exception;

public class ErrorDetails {
	private String message;
	private String details;
	public ErrorDetails(String message, String details) {
		super();
		this.message = message;
		this.details = details;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "ErrorDetails [message=" + message + ", details=" + details + "]";
	}
}