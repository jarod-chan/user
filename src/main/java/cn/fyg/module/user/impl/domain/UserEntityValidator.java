package cn.fyg.module.user.impl.domain;

import cn.fyg.module.user.UserException;
import cn.fyg.module.user.impl.infrastructure.validate.ValidateUtil;


public class UserEntityValidator {
	

	public static void validate(UserEntity user) {
		String message = ValidateUtil.validate(user);
		if(message!=null){
			throw new UserException(message);
		}
	}

}
