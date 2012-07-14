package cn.fyg.user.service;


public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserException(String message) {
		super(message);
	}
	
	

}
