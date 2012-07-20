package cn.fyg.user.service;

import cn.fyg.user.infrastructure.query.Query;
import cn.fyg.user.infrastructure.query.impl.User;


public interface UserQuery extends Query<UserQuery,User>  {
	
	UserQuery username();

	UserQuery realname();

	UserQuery email();

	UserQuery cellphone();
	
}
