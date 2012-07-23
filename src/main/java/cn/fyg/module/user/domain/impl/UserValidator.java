package cn.fyg.module.user.domain.impl;

import cn.fyg.module.user.UserException;
import cn.fyg.module.user.infrastructure.validate.ValidateUtil;


public class UserValidator {
	

	public static void validate(UserEntity user) {
		String message = ValidateUtil.validate(user);
		if(message!=null){
			throw new UserException(message);
		}
	}

}
