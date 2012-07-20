package demo;

import cn.fyg.user.domain.model.User;


public interface UserQuery extends Query<UserQuery,User>  {
	
	UserQuery username();

	UserQuery realname();

	UserQuery email();

	UserQuery cellphone();
	
}
