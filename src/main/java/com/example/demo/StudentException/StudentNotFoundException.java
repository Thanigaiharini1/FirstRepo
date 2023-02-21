

package com.example.demo.StudentException;
public class StudentNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public StudentNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public StudentNotFoundException() {
    }
}