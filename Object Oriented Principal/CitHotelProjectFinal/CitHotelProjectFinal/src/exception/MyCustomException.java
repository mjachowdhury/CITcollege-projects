package exception;

/**
 * @author Mohammed
 * @version 1.0
 * @since 2017/11/29
 */

//This is custom exception extending Exception Class
public class MyCustomException extends Exception {

	private static final long serialVersionUID = 1L;

	public MyCustomException(String details) {
		super(details);
	}
}
