package cn.fyg.module.user;


public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserException(String message) {
		super(message);
	}
	
	

}
