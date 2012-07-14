package cn.fyg.user.service;

public class UserLoginException extends UserException {

	private static final long serialVersionUID = 1L;

	public UserLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserLoginException(String message) {
		super(message);
	}

}
