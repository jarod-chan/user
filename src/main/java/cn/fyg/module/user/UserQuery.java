package cn.fyg.module.user;

import cn.fyg.module.user.query.Query;


public interface UserQuery extends Query<UserQuery,User>  {
	
	UserQuery username();

	UserQuery realname();

	UserQuery email();

	UserQuery cellphone();
	
}
