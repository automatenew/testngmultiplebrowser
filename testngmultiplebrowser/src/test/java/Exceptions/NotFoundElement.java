package Exceptions;

public class NotFoundElement extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundElement(Exception e)
	{
		System.out.println("Element Not Found");
	}
	
}
