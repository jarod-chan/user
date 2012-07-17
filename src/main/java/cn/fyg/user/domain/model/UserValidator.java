package cn.fyg.user.domain.model;

import java.util.List;

import cn.fyg.user.service.UserException;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;


public class UserValidator {

	public static void validate(User user) {
		Validator validator = new Validator();
		List<ConstraintViolation> violations = validator.validate(user);
		if(violations.size()>0){
			StringBuilder message=new StringBuilder();
			for (ConstraintViolation constraintViolation : violations) {
				message.append(constraintViolation.getMessage()+"\n");
			}
			throw new UserException(message.toString());
		}
	}

}
