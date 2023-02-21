package com.example.demo.StudentException;

public class StudentAlreadyExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	public  StudentAlreadyExistException(Integer id)
	{
		super("Student already Exist");
	}
	
	

}
