package cn.fyg.module.user.impl.infrastructure.validate;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

public class ValidateUtil {
	
	public static String validate(Object obj){
		Validator validator = new Validator();		
		List<ConstraintViolation> violations = validator.validate(obj);
		if(violations.size()>0){
			StringBuilder message=new StringBuilder();
			for (ConstraintViolation constraintViolation : violations) {
				message.append(constraintViolation.getMessage()+"\n");
			}
			return message.toString();
		}
		return null;
	}

}
