package cn.fyg.user.domain.model;

import cn.fyg.user.infrastructure.validate.ValidateUtil;
import cn.fyg.user.service.UserException;


public class UserValidator {
	

	public static void validate(User user) {
		String message = ValidateUtil.validate(user);
		if(message!=null){
			throw new UserException(message);
		}
	}

}
