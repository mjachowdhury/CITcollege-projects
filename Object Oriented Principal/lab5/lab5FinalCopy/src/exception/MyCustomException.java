package exception;
//This is custom exception extending Exception Class
public class MyCustomException extends Exception {
	public MyCustomException(String details) {
		super(details);
	}
}
