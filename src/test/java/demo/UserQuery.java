package demo;

import java.util.List;

import cn.fyg.user.domain.model.User;

public interface UserQuery {
	
	UserQuery username();

	UserQuery realname();

	UserQuery email();

	UserQuery cellphone();
	
	UserQuery like(Object obj);
	
	UserQuery equal(Object obj);
	
	UserQuery asc();
	
	UserQuery desc();
	
	List<User> list();
	
}
